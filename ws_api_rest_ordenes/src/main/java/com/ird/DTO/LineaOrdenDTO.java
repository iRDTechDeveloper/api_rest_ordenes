package com.ird.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineaOrdenDTO {

	private Long id;

	private ProductoDTO productoDTO;

	private Double precio;

	private Double cantidad;

	private Double totalDetalle;

}
