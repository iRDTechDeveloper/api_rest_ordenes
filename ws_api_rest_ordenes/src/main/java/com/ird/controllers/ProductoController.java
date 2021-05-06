package com.ird.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ird.DTO.ProductoDTO;
import com.ird.converters.ProductoConverter;
import com.ird.entity.Producto;
import com.ird.services.ProductoService;
import com.ird.utils.WrapperResponse;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService prodServices;

	@Autowired
	private ProductoConverter prodConvert;

	@GetMapping(value = "producto/{productoId}")
	public ResponseEntity<WrapperResponse<ProductoDTO>> findProductoById(@PathVariable("productoId") Long productoId) {

		Producto producto = prodServices.findProductoById(productoId);

		ProductoDTO productoDTO = prodConvert.fromEntity(producto);

		return new WrapperResponse<ProductoDTO>(true, "Successful", productoDTO).createResponse(HttpStatus.OK);

		// WrapperResponse<ProductoDTO> wpResponseDTO = new WrapperResponse<ProductoDTO>(true, "Successful", productoDTO);
		// return new ResponseEntity<WrapperResponse<ProductoDTO>>(wpResponseDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "deleteProducto/{productoId}")
	public ResponseEntity<?> deleteProducto(@PathVariable("productoId") Long productoId) {
		prodServices.deleteProducto(productoId);

		return new WrapperResponse(true, "Successful", null).createResponse(HttpStatus.OK);
	}

	@GetMapping(value = "productos")
	public ResponseEntity<List<ProductoDTO>> findAllProducto(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {

		Pageable pagina = PageRequest.of(pageNumber, pageSize);

		List<Producto> listaProductos = prodServices.findAllProducto(pagina);

		List<ProductoDTO> listaProductosDTO = prodConvert.fromEntity(listaProductos);

		return new WrapperResponse(true, "Successful", listaProductosDTO).createResponse(HttpStatus.OK);

		// return new ResponseEntity<List<ProductoDTO>>(listaProductosDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/nuevoProducto")
	public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO nuevoProducto) {

		Producto crearNuevoProducto = prodServices.producto_Cr_Up(prodConvert.fromDTO(nuevoProducto));

		ProductoDTO productoDTO = prodConvert.fromEntity(crearNuevoProducto);

		return new WrapperResponse(true, "Successful", productoDTO).createResponse(HttpStatus.CREATED);

		// return new ResponseEntity<ProductoDTO>(productoDTO, HttpStatus.CREATED);
	}

	@PutMapping(value = "/updateProducto")
	public ResponseEntity<ProductoDTO> updateProducto(@RequestBody ProductoDTO updateProducto) {

		Producto productoExistente = prodServices.producto_Cr_Up(prodConvert.fromDTO(updateProducto));

		ProductoDTO productoDTO = prodConvert.fromEntity(productoExistente);

		return new WrapperResponse(true, "Successful", productoDTO).createResponse(HttpStatus.OK);

		// return new ResponseEntity<ProductoDTO>(productoDTO, HttpStatus.OK);
	}

}
