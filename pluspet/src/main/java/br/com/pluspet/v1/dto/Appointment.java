package br.com.pluspet.v1.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Appointment {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID id;

	@NotNull(message = "Tipo de serviço {jakarta.validation.constraints.NotNull.message}")
	private String type;

	@NotNull(message = "Data de atendimento {jakarta.validation.constraints.NotNull.message}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	private String additionalDetails;

	private String comment;

	@NotNull(message = "Pet {jakarta.validation.constraints.NotNull.message}")
	private @Valid PetAppointment pet;

	@JsonProperty(access = Access.READ_ONLY)
	private String status;

}
