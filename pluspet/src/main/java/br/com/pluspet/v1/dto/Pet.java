package br.com.pluspet.v1.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Pet {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID id;

	@NotBlank(message = "Nome {jakarta.validation.constraints.NotBlank.message}")
	private String name;

	private LocalDate birthDate;

	@NotBlank(message = "Tipo de animal {jakarta.validation.constraints.NotBlank.message}")
	private String type;

	@NotBlank(message = "Raça {jakarta.validation.constraints.NotBlank.message}")
	private String breed;

	@JsonProperty(access = Access.READ_ONLY)
	private Boolean archived = false;

	@NotNull(message = "Tutor {jakarta.validation.constraints.NotNull.message}")
	private Tutor tutor;
}
