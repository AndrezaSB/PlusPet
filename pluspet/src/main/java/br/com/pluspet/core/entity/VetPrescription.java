package br.com.pluspet.core.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vet_prescription")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VetPrescription {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "veterinarian")
	@NotNull(message = "Veterinário {jakarta.validation.constraints.NotNull.message}")
	private Employee veterinarian;

	@ManyToOne
	@JoinColumn(name = "appointment")
	@NotNull(message = "Consulta {jakarta.validation.constraints.NotNull.message}")
	private Appointment appointment;

	@OneToMany(mappedBy = "prescription")
	@NotNull(message = "Medicamentos {jakarta.validation.constraints.NotNull.message}")
	private List<Medication> medications;
}
