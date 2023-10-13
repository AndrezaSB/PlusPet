package br.com.pluspet.api.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "PlusPet Health & Care API ", description = "PlusPet API Documentation", version = "1.0"), servers = {
		@Server(description = "Local Env", url = "http://localhost:8080"),
		@Server(description = "Prod Env", url = "https://pluspet.ashystone-e3d6e0e7.eastus.azurecontainerapps.io/") }, security = {
				@SecurityRequirement(name = "bearer-key") })
public class OpenApiConfiguration {

	@Bean
	public OpenApiCustomizer openApiCustomizer() {

		return openApi -> openApi.getComponents().addSecuritySchemes("bearer-key",
				new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));
	}
}
