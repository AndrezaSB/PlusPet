package br.com.pluspet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "PlusPet API", version = "1.0"))
public class PluspetApplication {

	public static void main(String[] args) {
		SpringApplication.run(PluspetApplication.class, args);
	}

}
