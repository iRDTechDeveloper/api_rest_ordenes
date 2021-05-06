package com.ird.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductoDTO {
	
	private Long id;
	
	private String nombre;
	
	private String categoria;
	
	private Double precio;
}
