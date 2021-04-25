package com.ird.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Producto {

	private Long id;
	private String nombre;
	private String categoria;
	
}
