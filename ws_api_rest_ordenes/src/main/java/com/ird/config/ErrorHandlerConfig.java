package com.ird.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ird.exceptions.GeneralServiceException;
import com.ird.exceptions.NoDataFoundException;
import com.ird.exceptions.ValidateServiceException;
import com.ird.utils.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> allException(Exception e, WebRequest request) {
		log.error(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, "INTERNAL SERVER ERROR", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request) {
		log.info(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request) {
		log.info(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, e.getMessage(), null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(GeneralServiceException.class)
	public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request) {
		log.error(e.getMessage(), e);
		WrapperResponse<?> response = new WrapperResponse<>(false, "INTERNAL SERVER ERROR", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
