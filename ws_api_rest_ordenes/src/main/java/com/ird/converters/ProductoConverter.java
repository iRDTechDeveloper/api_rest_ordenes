package com.ird.converters;

import com.ird.DTO.ProductoDTO;
import com.ird.entity.Producto;

public class ProductoConverter extends Converter<Producto, ProductoDTO> {

	@Override
	public ProductoDTO fromEntity(Producto entity) {
		
		if (entity == null) return null;

		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.categoria(entity.getCategoria())
				.precio(entity.getPrecio())
				.build();
	}

	@Override
	public Producto fromDTO(ProductoDTO dto) {
		
		if (dto == null) return null;
		
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.categoria(dto.getCategoria())
				.precio(dto.getPrecio())
				.build();
	}

}
