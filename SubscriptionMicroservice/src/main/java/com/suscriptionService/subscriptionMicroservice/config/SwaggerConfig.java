package com.suscriptionService.subscriptionMicroservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	public SwaggerConfigProperties swaggerConfigProperties;
	
	@Bean
	public Docket subscriptionApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.suscriptionService.subscriptionMicroservice.web.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo(
				swaggerConfigProperties.getTitle(),
				swaggerConfigProperties.getDescription(),
				swaggerConfigProperties.getVersion(),
				swaggerConfigProperties.getTermsOfUse(),
				new Contact(swaggerConfigProperties.getContact().getName(), 
						swaggerConfigProperties.getContact().getWeb(),
						swaggerConfigProperties.getContact().getEmail()),
				swaggerConfigProperties.getLicence().getType(),
				swaggerConfigProperties.getLicence().getUrl());
		return apiInfo;
	}
	
	@Bean
	public UiConfiguration uiConfig() {
	    String[] supportedMethods = {};
	    return new UiConfiguration(null, supportedMethods);
	}

}
