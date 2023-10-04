package br.com.pluspet.v1.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PetTutorRequest extends Tutor {

	@NotNull(message = "Identificador Tutor {jakarta.validation.constraints.NotNull.message}")
	private UUID id;
}
