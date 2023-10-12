package br.com.pluspet.v1.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.Valid;
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

	@NotBlank(message = "Espécie {jakarta.validation.constraints.NotBlank.message}")
	private String spieces;

	@NotNull(message = "Sexo {jakarta.validation.constraints.NotBlank.message}")
	private String gender;

	@NotBlank(message = "Raça {jakarta.validation.constraints.NotBlank.message}")
	private String breed;

	@JsonProperty(access = Access.READ_ONLY)
	private Boolean archived = false;

	@NotNull(message = "Tutor {jakarta.validation.constraints.NotNull.message}")
	private @Valid TutorBasicInfo tutor;

}
