package com.ird.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ird.entity.Producto;
import com.ird.repository.ProductoRepository;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoRepository prodRepo;
	
	@GetMapping(value = "producto/{productoId}")
	public ResponseEntity<Producto> findProductoById(@PathVariable("productoId") Long productoId) {
		Producto producto = prodRepo.findById(productoId)
				.orElseThrow(() -> new RuntimeException("No Existe El Producto..."));
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);	
	}
	
	@DeleteMapping(value = "deleteProducto/{productoId}")
	public ResponseEntity<Void> deleteProducto(@PathVariable("productoId") Long productoId) {
		Producto producto = prodRepo.findById(productoId)
				.orElseThrow(() -> new RuntimeException("No Existe El Producto..."));
		prodRepo.delete(producto);
		return new ResponseEntity(producto, HttpStatus.OK);
	}
	
	@GetMapping(value = "productos")
	public ResponseEntity<List<Producto>> findAllProducto(){
		List<Producto> listaProductos = prodRepo.findAll();
		return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevoProducto")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto nuevoProducto) {
		Producto crearNuevoProducto = prodRepo.save(nuevoProducto);
		return new ResponseEntity<Producto>(crearNuevoProducto, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateProducto")
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto updateProducto) {
		Producto productoExistente = prodRepo.findById(updateProducto.getId())
				.orElseThrow(() -> new RuntimeException("No Existe El Producto..."));
		
		productoExistente.setNombre(updateProducto.getNombre());
		productoExistente.setCategoria(updateProducto.getCategoria());
		productoExistente.setPrecio(updateProducto.getPrecio());
		
		prodRepo.save(productoExistente);
		
		return new ResponseEntity<Producto>(productoExistente, HttpStatus.OK);
	}
	
}
