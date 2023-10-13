package br.com.pluspet.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "veterinarian")
@PrimaryKeyJoinColumn(name = "id")
@Data
@EqualsAndHashCode(callSuper = true)
public class Veterinarian extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7162874308913224165L;

	@Column(name = "crmv")
	@NotBlank(message = "CRMV {jakarta.validation.constraints.NotBlank.message}")
	private String CRMV;

}
