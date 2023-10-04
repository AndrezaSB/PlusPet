package br.com.pluspet.v1.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TutorRequest extends Tutor {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID id;

	@NotEmpty(message = "Endereço {jakarta.validation.constraints.NotEmpty.message}")
	private List<Address> addresses = new ArrayList<Address>();

	@NotEmpty(message = "Telefone {jakarta.validation.constraints.NotEmpty.message}")
	private List<Telephone> telephones;
}
