package com.ird.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ird.converters.OrdenConverter;
import com.ird.converters.ProductoConverter;
import com.ird.converters.UserConverter;

@Configuration
public class Config {

	@Value("${config.datetimeFormat}")
	private String formatoFechaHora;

	@Bean
	public ProductoConverter getProductoConverter() {
		return new ProductoConverter();
	}

	@Bean
	public OrdenConverter getOrdenConverter() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoFechaHora);
		return new OrdenConverter(formatoFecha, getProductoConverter(), getUserConverter());
	}
	
	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}

}