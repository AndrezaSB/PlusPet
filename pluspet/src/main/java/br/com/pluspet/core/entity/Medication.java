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
@Table(name = "medication")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medication {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "name")
	@NotBlank(message = "Nome medicamento {jakarta.validation.constraints.NotBlank.message}")
	private String name;

	@Column(name = "dosage")
	@NotBlank(message = "Dosagem {jakarta.validation.constraints.NotBlank.message}")
	private String dosage;

	@Column(name = "frequency")
	@NotBlank(message = "Frequência de uso {jakarta.validation.constraints.NotBlank.message}")
	private String frequency;

	@Column(name = "period_of_use")
	@NotBlank(message = "Período de uso {jakarta.validation.constraints.NotBlank.message}")
	private String periodOfUse;

	@Column(name = "additional_details")
	private String additionalDetails;

	@ManyToOne
	@JoinColumn(name = "prescription")
	@NotNull(message = "Prescrição {jakarta.validation.constraints.NotNull.message}")
	private VetPrescription prescription;

}
