package com.ird.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ird.entity.Producto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SaludoController {

	@GetMapping(value = "Saludo")
	public String Saludar() {
		return "Saludos, Iniciando Curso API REST con Spring Boot";
	}
	
	/*@GetMapping(value = "Producto")
	public Producto findProducto() {
		Producto producto = new Producto();
		producto.setId(1L);
		producto.setNombre("Producto 1");
		producto.setCategoria("Categoria 1");
		return producto;
	*/
	
	@GetMapping(value = "Producto")
	public Producto findProducto() {
		log.info("Ejecutando Metodo => findProducto()");
		Producto producto = Producto.builder()
				.id(1L)
				.nombre("Producto 2")
				.categoria("Categoria 2")
				.build();
		return producto;
	}
}
