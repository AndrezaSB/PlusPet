package br.com.pluspet.core.entity;

import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "cep")
	@NotNull(message = "CEP {jakarta.validation.constraints.NotNull.message}")
	private Integer cep;

	@Column(name = "street_name")
	@NotBlank(message = "Rua/Avenida {jakarta.validation.constraints.NotBlank.message}")
	private String streetName;

	@Column(name = "number")
	@NotBlank(message = "Número {jakarta.validation.constraints.NotBlank.message}")
	private String number;

	@Column(name = "district")
	@NotBlank(message = "Município {jakarta.validation.constraints.NotBlank.message}")
	private String district;

	@Column(name = "city")
	@NotBlank(message = "Cidade {jakarta.validation.constraints.NotBlank.message}")
	private String city;

	@Column(name = "additional_information")
	private String additionalInformation;

	@ManyToOne
	@JoinColumn(name = "tutor")
	@NotNull(message = "Tutor {jakarta.validation.constraints.NotNull.message}")
	private Tutor tutor;

}
