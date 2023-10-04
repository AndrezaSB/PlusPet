package br.com.pluspet.v1.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class Tutor {

	@NotBlank(message = "Nome {jakarta.validation.constraints.NotBlank.message}")
	private String name;

	private LocalDate birthDate;

	@NotBlank(message = "CPF {jakarta.validation.constraints.NotBlank.message}")
	private String cpf;

	@NotBlank(message = "E-mail {jakarta.validation.constraints.NotBlank.message}")
	private String email;

	@JsonProperty(access = Access.READ_ONLY)
	private Boolean archived = false;
}
