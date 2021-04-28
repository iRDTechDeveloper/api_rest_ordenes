package com.ird.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ird.entity.Producto;
import com.ird.repository.ProductoRepository;
import com.ird.validator.ProductoValidator;

@Service
public class ProductoService {
	
	@Autowired
	private ProductoRepository prodRepo;
	
	public List<Producto> findAllProducto(Pageable pagina){
		List<Producto> listaProductos = prodRepo.findAll(pagina).toList();
		return listaProductos;
	}
	
	public Producto findProductoById(Long productoId) {
		Producto producto = prodRepo.findById(productoId)
				.orElseThrow(() -> new RuntimeException("No Existe El Producto..."));
		return producto;	
	}
	
	@Transactional
	public void deleteProducto(Long productoId) {
		Producto producto = prodRepo.findById(productoId)
				.orElseThrow(() -> new RuntimeException("No Existe El Producto..."));
		prodRepo.delete(producto);
	}
	
	@Transactional
	public Producto producto_Cr_Up(Producto productoCU) {
		
		ProductoValidator.validarInsert(productoCU);
		
		if(productoCU.getId() == null) {
			Producto crearNuevoProducto = prodRepo.save(productoCU);
			return crearNuevoProducto;
		}
		
		Producto productoExistente = prodRepo.findById(productoCU.getId())
				.orElseThrow(() -> new RuntimeException("No Existe El Producto..."));
		
		productoExistente.setNombre(productoCU.getNombre());
		productoExistente.setCategoria(productoCU.getCategoria());
		productoExistente.setPrecio(productoCU.getPrecio());
		
		prodRepo.save(productoExistente);
		
		return productoExistente;	
	}
}
