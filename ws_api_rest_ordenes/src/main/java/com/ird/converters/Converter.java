package com.ird.converters;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<E, D> {

	public abstract D fromEntity(E entity); // -> Convierte de Entidad a DTO

	public abstract E fromDTO(D dto); // -> Convierte de DTO a Entidad.

	public List<D> fromEntity(List<E> convertEntity) { // -> Convierte lista de tipo Entity a listas tipo DTO
		return convertEntity.stream().map(e -> fromEntity(e)).collect(Collectors.toList());
	}

	public List<E> fromDTO(List<D> convertDTO) { // -> Convierte lista de tipo DTO a listas tipo Entity
		return convertDTO.stream().map(d -> fromDTO(d)).collect(Collectors.toList());
	}
}
