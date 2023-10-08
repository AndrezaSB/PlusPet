package br.com.pluspet.v1.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Address {

	private UUID id;

	@NotNull(message = "CEP {jakarta.validation.constraints.NotNull.message}")
	private Integer cep;

	@NotBlank(message = "Rua/Avenida {jakarta.validation.constraints.NotBlank.message}")
	private String streetName;

	@NotBlank(message = "Número {jakarta.validation.constraints.NotBlank.message}")
	private String number;

	@NotBlank(message = "Município {jakarta.validation.constraints.NotBlank.message}")
	private String district;

	@NotBlank(message = "Cidade {jakarta.validation.constraints.NotBlank.message}")
	private String city;

	private String additionalInformation;

}
