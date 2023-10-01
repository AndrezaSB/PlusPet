package br.com.pluspet.core.entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "vet_prescription")
@Data
public class VetPrescription {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;
	
	@ManyToOne
	@JoinColumn(name = "appointment")
	@NotNull(message = "Consulta {jakarta.validation.constraints.NotNull.message}")
	private VetAppointment appointment;

	@OneToMany(mappedBy = "prescription")
	@NotNull(message = "Medicamentos {jakarta.validation.constraints.NotNull.message}")
	private List<Medication> medications;
}
