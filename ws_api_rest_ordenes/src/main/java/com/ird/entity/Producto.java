package com.ird.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCTOS")
public class Producto {

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NOMBRE", nullable = false, length = 50)
	private String nombre;

	@Column(name = "CATEGORIA", nullable = false, length = 100)
	private String categoria;

	@Column(name = "PRECIO", nullable = false)
	private Double precio;

}
