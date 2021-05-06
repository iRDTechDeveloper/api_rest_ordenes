package com.ird.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.description("Token de Autenticacion")
						.modelRef(
								new ModelRef("String"))
						.parameterType("header")
						.required(false)
						.build()
						))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ird.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo()
				 );
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"API REST SERVICIO ORDENES", 
				"API REST SERVICIO ORDENES", 
				"V_1.0", 
				"http://ird.com/terms", 
				new Contact("iRD", "https://ird.com", "irdtechdeveloper@gmail.com"), 
				"LICENCIA IRD TECH DEV", 
				"LICENCIA IRD URL",
				Collections.emptyList()
				);
	}
}
