package com.ird.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ird.entity.Producto;
import com.ird.exceptions.GeneralServiceException;
import com.ird.exceptions.NoDataFoundException;
import com.ird.exceptions.ValidateServiceException;
import com.ird.repository.ProductoRepository;
import com.ird.validator.ProductoValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

	@Autowired
	private ProductoRepository prodRepo;

	public List<Producto> findAllProducto(Pageable pagina) {

		try {

			List<Producto> listaProductos = prodRepo.findAll(pagina).toList();
			return listaProductos;

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public Producto findProductoById(Long productoId) {
		try {

			Producto producto = prodRepo.findById(productoId).orElseThrow(
					() -> new NoDataFoundException("No Existe El Producto Buscado Por El Id Ingresado..."));
			return producto;

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void deleteProducto(Long productoId) {

		try {

			Producto producto = prodRepo.findById(productoId)
					.orElseThrow(() -> new NoDataFoundException("No Existe El Producto Que Desea Eliminar..."));
			prodRepo.delete(producto);

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public Producto producto_Cr_Up(Producto productoCU) {

		try {

			ProductoValidator.validarInsert(productoCU);

			if (productoCU.getId() == null) {
				Producto crearNuevoProducto = prodRepo.save(productoCU);
				return crearNuevoProducto;
			}

			Producto productoExistente = prodRepo.findById(productoCU.getId())
					.orElseThrow(() -> new NoDataFoundException("No Existe El Producto Que Desea Actualizar..."));

			productoExistente.setNombre(productoCU.getNombre());
			productoExistente.setCategoria(productoCU.getCategoria());
			productoExistente.setPrecio(productoCU.getPrecio());

			prodRepo.save(productoExistente);

			return productoExistente;

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
}
