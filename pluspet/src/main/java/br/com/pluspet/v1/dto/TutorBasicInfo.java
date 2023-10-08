package br.com.pluspet.v1.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TutorBasicInfo {

	@NotNull(message = "Id {jakarta.validation.constraints.NotBlank.message}")
	private UUID id;

	private String name;

	private LocalDate birthDate;

	private String cpf;

	private String email;

	@JsonProperty(access = Access.READ_ONLY)
	private Boolean archived = false;

}
