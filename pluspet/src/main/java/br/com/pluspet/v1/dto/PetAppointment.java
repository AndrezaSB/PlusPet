package br.com.pluspet.v1.dto;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PetAppointment {

	@NotNull(message = "id Pet {jakarta.validation.constraints.NotBlank.message}")
	private UUID id;

	@JsonProperty(access = Access.READ_ONLY)
	private String name;

	@JsonProperty(access = Access.READ_ONLY)
	private String tutorName;

}
