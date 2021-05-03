package com.ird.converters;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.ird.DTO.LineaOrdenDTO;
import com.ird.DTO.OrdenDTO;
import com.ird.entity.LineaOrden;
import com.ird.entity.Orden;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrdenConverter extends Converter<Orden, OrdenDTO> {

	private DateTimeFormatter datoFormatoFecha;
	
	private ProductoConverter productoConverter;
	
	@Override
	public OrdenDTO fromEntity(Orden entity) {
		if (entity == null) return null;
		
		List<LineaOrdenDTO> listLineaOrdenDTO = fromlineaOrdenEntity(entity.getLineaOrden());
		return OrdenDTO.builder()
				.id(entity.getId())
				.lineaOrdenDTO(listLineaOrdenDTO)
				.fechReg(entity.getFechReg().format(datoFormatoFecha))
				.totalPedido(entity.getTotalPedido())
				.build();
	}

	@Override
	public Orden fromDTO(OrdenDTO dto) {
		if (dto == null) return null;
		
		List<LineaOrden> listLineaOrden = fromlineaOrdenDTO(dto.getLineaOrdenDTO());
		return Orden.builder()
				.id(dto.getId())
				.lineaOrden(listLineaOrden)
				.totalPedido(dto.getTotalPedido())
				.build();
	}
	
	private List<LineaOrdenDTO> fromlineaOrdenEntity(List<LineaOrden> lineaOrden) {
		if (lineaOrden == null) return null;
		
		return lineaOrden.stream().map(lineaOrdenes -> {
			return LineaOrdenDTO.builder()
					.id(lineaOrdenes.getId())
					.precio(lineaOrdenes.getPrecio())
					.productoDTO(productoConverter.fromEntity(lineaOrdenes.getProducto()))
					.cantidad(lineaOrdenes.getCantidad())
					.totalDetalle(lineaOrdenes.getTotalDetalle())
					.build();
		}).collect(Collectors.toList());
	}
	
	private List<LineaOrden> fromlineaOrdenDTO(List<LineaOrdenDTO> lineaOrdenDTO) {
		if (lineaOrdenDTO == null) return null;
		
		return lineaOrdenDTO.stream().map(lineOrDTO ->{
			return LineaOrden.builder()
					.id(lineOrDTO.getId())
					.precio(lineOrDTO.getPrecio())
					.producto(productoConverter.fromDTO(lineOrDTO.getProductoDTO()))
					.cantidad(lineOrDTO.getCantidad())
					.totalDetalle(lineOrDTO.getTotalDetalle())
					.build();
		}).collect(Collectors.toList());
	}

}
