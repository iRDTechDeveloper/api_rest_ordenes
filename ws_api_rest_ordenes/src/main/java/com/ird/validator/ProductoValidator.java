package com.ird.validator;

import com.ird.entity.Producto;
import com.ird.exceptions.ValidateServiceException;

public class ProductoValidator {

	public static void validarInsert(Producto validarProducto) {
		
		if(validarProducto.getNombre() == null || validarProducto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("EL nombre es requerido!!! ...");
		}
		
		if(validarProducto.getNombre().length() > 50 || validarProducto.getNombre().length() < 3) {
			throw new ValidateServiceException("EL valor del nombre es incorrecto, (Min 3 Digitos)-(Max 50 Digitos)!!! ...");
		}
		
		if(validarProducto.getCategoria() == null || validarProducto.getCategoria().trim().isEmpty()) {
			throw new ValidateServiceException("EL nombre de la categoria es requerido...");
		}
		
		if(validarProducto.getCategoria().length() > 100 || validarProducto.getCategoria().length() < 5) {
			throw new ValidateServiceException("EL valor de la categoria es incorrecto, (Min 5 Digitos)-(Max 100 Digitos)!!! ...");
		}
		
		if(validarProducto.getPrecio() == null || validarProducto.getPrecio() < 0) {
			throw new ValidateServiceException("EL valor del precio es incorrecto, digite un valor correcto!!! ...");
		}
	}
}
