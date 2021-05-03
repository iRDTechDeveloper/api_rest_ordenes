package com.ird.validator;

import com.ird.entity.LineaOrden;
import com.ird.entity.Orden;
import com.ird.exceptions.ValidateServiceException;

public class OrdenValidator {

	public static void validarInsert(Orden orden) {

		/*if (orden.getTotalPedido() == null || orden.getTotalPedido() < 0) {
			throw new ValidateServiceException("EL Total Es Incorrecto!!! ...");
		}*/

		if (orden.getLineaOrden().isEmpty() || orden.getLineaOrden() == null) {
			throw new ValidateServiceException("Las Lineas de Ordenes Son requerido!!! ...");
		}

		for (LineaOrden lineaOrden : orden.getLineaOrden()) {
			
			/*if (lineaOrden.getPrecio() == null || lineaOrden.getPrecio() < 0) {
				throw new ValidateServiceException("El Valor Del Precio Es Incorrecto!!! ...");
			}*/
			
			if(lineaOrden.getCantidad() == null || lineaOrden.getCantidad() < 0) {
				throw new ValidateServiceException("El Valor De La Cantidad Es Incorrecto!!! ...");
			}
			
			/*if(lineaOrden.getTotalDetalle() == null || lineaOrden.getTotalDetalle() < 0) {
				throw new ValidateServiceException("El Valor Total Del Pedido Es Incorrecto!!! ...");
			}*/
			
			if(lineaOrden.getProducto() == null || lineaOrden.getProducto().getId() == null) {
				throw new ValidateServiceException("El Producto Es Requerido!!! ...");
			}
		}
	}

}
