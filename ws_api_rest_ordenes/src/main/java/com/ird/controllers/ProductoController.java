package com.ird.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ird.entity.Producto;

@RestController
public class ProductoController {

	private List<Producto> productos = new ArrayList<>();
	
	public ProductoController() {
		for (int cont = 0; cont < 10; cont++) {
			productos.add(Producto.builder()
					.id((cont + 1L))
					.nombre("Producto" + (cont + 1L))
					.categoria("Categoria" + (cont + 1L))
					.build()
					);
		}
	}
	
	@GetMapping(value = "producto/{productoId}")
	public Producto findProductoById(@PathVariable("productoId") Long productoId) {
		for (Producto producto : this.productos) {
			if (producto.getId().longValue() == productoId.longValue()) {
				return producto;
			}
		}
		return null;
	}
	
	@DeleteMapping(value = "deleteProducto/{productoId}")
	public void deleteProducto(@PathVariable("productoId") Long productoId) {
		Producto eliminarProducto = null;
		
		for (Producto producto : this.productos) {
			
			if (producto.getId().longValue() == productoId.longValue()) {
				eliminarProducto = producto;
				break;
			}
		}
		if(eliminarProducto == null ) throw new RuntimeException("No Existe El Producto");
		
		this.productos.remove(eliminarProducto);
	}
	
	@GetMapping(value = "productos")
	public List<Producto> findAllProducto(){
		return this.productos;
	}
	
	@PostMapping(value = "/nuevoProducto")
	public Producto createProducto(@RequestBody Producto nuevoProducto) {
		this.productos.add(nuevoProducto);
		return nuevoProducto;
	}
	
	@PutMapping(value = "/updateProducto")
	public Producto updateProducto(@RequestBody Producto updateProducto) {
		for (Producto producto : this.productos) {
			if (producto.getId().longValue() == updateProducto.getId().longValue()) {
				producto.setNombre(updateProducto.getNombre());
				producto.setCategoria(updateProducto.getCategoria());
				return producto;
			}
		}
		throw new RuntimeException("No Existe El Producto");
	}
	
}
