package com.ird.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ird.DTO.OrdenDTO;
import com.ird.converters.OrdenConverter;
import com.ird.entity.Orden;
import com.ird.services.OrdenService;
import com.ird.utils.WrapperResponse;

@RestController
public class OrdenControllers {
	
	@Autowired
	private OrdenConverter ordenConvert;
	
	@Autowired
	private OrdenService ordenService;

	@GetMapping(value = "ordenes")
	public ResponseEntity<WrapperResponse<List<OrdenDTO>>> findAllOrdenes(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {		
		Pageable pagina = PageRequest.of(pageNumber, pageSize);		
		List<Orden> orden = ordenService.findAllOrdenes(pagina);		
		return new WrapperResponse(true, "Successful", ordenConvert.fromEntity(orden))
				.createResponse();
	}
	
	@GetMapping(value = "orden/{ordenId}")
	public ResponseEntity<WrapperResponse<OrdenDTO>> findOrdenById(@PathVariable("ordenId") Long ordenId) {
		Orden orden = ordenService.findOrdenById(ordenId);
		OrdenDTO ordenDTO = ordenConvert.fromEntity(orden);
		return new WrapperResponse<OrdenDTO>(true, "Successful", ordenDTO)
				.createResponse(HttpStatus.OK);
	}
	
	@PostMapping(value = "/nuevaOrden")
	public ResponseEntity<WrapperResponse<OrdenDTO>> createOrden(@RequestBody OrdenDTO nuevaOrden) {
		Orden crearNuevaOrden = ordenService.orden_Cr_Up(ordenConvert.fromDTO(nuevaOrden));
		OrdenDTO ordenDTO = ordenConvert.fromEntity(crearNuevaOrden);
		return new WrapperResponse<OrdenDTO>(true, "Successful", ordenDTO)
				.createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/updateOrden")
	public ResponseEntity<WrapperResponse<OrdenDTO>> updateOrden(@RequestBody OrdenDTO updateOrden) {
		Orden ordenExistente = ordenService.orden_Cr_Up(ordenConvert.fromDTO(updateOrden));
		OrdenDTO ordenDTO = ordenConvert.fromEntity(ordenExistente);
		return new WrapperResponse(true, "Successful", ordenDTO)
				.createResponse(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "deleteOrden/{ordenId}")
	public ResponseEntity<WrapperResponse<?>> deleteOrden(@PathVariable("ordenId") Long ordenId) {	
		ordenService.deleteOrden(ordenId);
		return new WrapperResponse(true, "Successful", null)
				.createResponse(HttpStatus.OK);
	}
	
}
