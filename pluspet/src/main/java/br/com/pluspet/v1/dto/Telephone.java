package br.com.pluspet.v1.dto;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Telephone {

	private UUID id;

	@Column(name = "description")
	private String description;

	@NotBlank(message = "Número do telefone {jakarta.validation.constraints.NotBlank.message}")
	private String phoneNumber;

}
