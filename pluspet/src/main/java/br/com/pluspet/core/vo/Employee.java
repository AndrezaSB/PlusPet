package br.com.pluspet.core.vo;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.pluspet.core.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

	@JsonProperty(access = Access.READ_ONLY)
	private UUID id;
	private String name;
	private String login;
	private Role role;

}
