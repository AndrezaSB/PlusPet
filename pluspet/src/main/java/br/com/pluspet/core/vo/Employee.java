package br.com.pluspet.core.vo;

import java.util.UUID;

import br.com.pluspet.core.enums.Role;
import lombok.Data;

@Data
public class Employee {

	private UUID id;
	private String name;
	private String login;
	private String CRMV;
	private Role role;

}
