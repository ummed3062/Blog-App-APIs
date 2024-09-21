package com.nagarro.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	private ApiKey apiKeys() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}
//	
//	It is use for access the APIs from swagger documentation OR
//	JWT configuration in swagger UI so use the two method 
//	1. securityContexts(null) 2. securitySchemes(Arrays.asList(apiKeys()))
	private List<SecurityContext> securityContexts(){
		return Arrays.asList(SecurityContext.builder().securityReferences(securityRef()).build()); 
	}
	
	private List<SecurityReference> securityRef(){
		
		AuthorizationScope scope = new AuthorizationScope("global", "AccessEverything");
		return Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[] {scope}));
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getInfo())
				.securityContexts(securityContexts())
				.securitySchemes(Arrays.asList(apiKeys()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo getInfo() {

		return new ApiInfo("Blogging Application : APIs", "Developed by Ummed Singh", "1.0", "Terms of service",
				new Contact("Ummed Singh", "https://linkedin.com/in/ummed-singh-a4901717b/",
						"ummedsingh3062000@gmail.com"),
				"License of APIs", "APIs license URL", Collections.emptyList());
	}
}
