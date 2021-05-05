package com.ird.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ird.config.security.UserPrincipal;
import com.ird.entity.LineaOrden;
import com.ird.entity.Orden;
import com.ird.entity.Producto;
import com.ird.entity.User;
import com.ird.exceptions.GeneralServiceException;
import com.ird.exceptions.NoDataFoundException;
import com.ird.exceptions.ValidateServiceException;
import com.ird.repository.LineaOrdenesRepository;
import com.ird.repository.OrdenRepository;
import com.ird.repository.ProductoRepository;
import com.ird.validator.OrdenValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdenService {

	@Autowired
	private OrdenRepository ordenRepo;

	@Autowired
	private LineaOrdenesRepository lineaOrdenRepo;

	@Autowired
	private ProductoRepository prodRepo;

	public List<Orden> findAllOrdenes(Pageable pagina) {
		try {
			List<Orden> listaOrdenes = ordenRepo.findAll(pagina).toList();
			return listaOrdenes;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public Orden findOrdenById(Long ordenId) {
		try {
			Orden orden = ordenRepo.findById(ordenId)
					.orElseThrow(() -> new NoDataFoundException("No Existe La Orden Buscada Por El Id Ingresado..."));
			return orden;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void deleteOrden(Long ordenId) {
		try {
			Orden orden = ordenRepo.findById(ordenId)
					.orElseThrow(() -> new NoDataFoundException("No Existe La Orden Que Desea Eliminar..."));
			ordenRepo.delete(orden);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public Orden orden_Cr_Up(Orden ordenCU) {
		try {
			double TotalPedido = 0;
			OrdenValidator.validarInsert(ordenCU);
			
			User user = UserPrincipal.getCurrentUser();

			for (LineaOrden lineaOrden : ordenCU.getLineaOrden()) {
				Producto producto = prodRepo.findById(lineaOrden.getProducto().getId())
				.orElseThrow(() -> new NoDataFoundException("No Existe El Producto " + lineaOrden.getProducto().getId()));
				
				lineaOrden.setPrecio(producto.getPrecio());
				lineaOrden.setTotalDetalle((producto.getPrecio() * lineaOrden.getCantidad()));				
				TotalPedido += lineaOrden.getTotalDetalle();				
			}
			ordenCU.setTotalPedido(TotalPedido);

			ordenCU.getLineaOrden().forEach(lineaOrden -> lineaOrden.setOrden(ordenCU));
			if (ordenCU.getId() == null) {
				ordenCU.setUserDTO(user);
				ordenCU.setFechReg(LocalDateTime.now());
				Orden crearNuevaOrden = ordenRepo.save(ordenCU);
				return crearNuevaOrden;
			}
			Orden ordenExistente = ordenRepo.findById(ordenCU.getId())
					.orElseThrow(() -> new NoDataFoundException("La Orden Que Desea Actualizar No Existe..."));
			ordenCU.setFechReg(ordenExistente.getFechReg());

			List<LineaOrden> eliminarLineaPedido = ordenExistente.getLineaOrden();
			eliminarLineaPedido.removeAll(ordenCU.getLineaOrden());
			lineaOrdenRepo.deleteAll(eliminarLineaPedido);

			return ordenRepo.save(ordenCU);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

}
