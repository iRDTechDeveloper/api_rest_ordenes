package com.ird.controllers;

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
import com.ird.services.ProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService prodServices;
	
	@GetMapping(value = "producto/{productoId}")
	public ResponseEntity<Producto> findProductoById(@PathVariable("productoId") Long productoId) {
		Producto producto = prodServices.findProductoById(productoId);
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);	
	}
	
	@DeleteMapping(value = "deleteProducto/{productoId}")
	public ResponseEntity<Void> deleteProducto(@PathVariable("productoId") Long productoId) {
		prodServices.deleteProducto(productoId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping(value = "productos")
	public ResponseEntity<List<Producto>> findAllProducto(){
		List<Producto> listaProductos = prodServices.findAllProducto();
		return new ResponseEntity<List<Producto>>(listaProductos, HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevoProducto")
	public ResponseEntity<Producto> createProducto(@RequestBody Producto nuevoProducto) {
		Producto crearNuevoProducto = prodServices.producto_Cr_Up(nuevoProducto);
		return new ResponseEntity<Producto>(crearNuevoProducto, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateProducto")
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto updateProducto) {
		Producto productoExistente = prodServices.producto_Cr_Up(updateProducto);
		return new ResponseEntity<Producto>(productoExistente, HttpStatus.OK);
	}
	
}
