package br.com.pluspet.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "veterinarian")
@Data
@EqualsAndHashCode(callSuper = true)
public class Veterinarian extends Employee {

	@Column(name = "crmv")
	@NotBlank(message = "CRMV {jakarta.validation.constraints.NotBlank.message}")
	private String CRMV;

}
