package com.ird.DTO;

import java.util.List;

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
public class OrdenDTO {

	private Long id;
	private String fechReg;
	private List<LineaOrdenDTO> lineaOrdenDTO;
	private Double totalPedido;
	private UserDTO userDTO;

}
