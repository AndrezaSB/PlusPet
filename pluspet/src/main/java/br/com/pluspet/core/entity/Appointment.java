package br.com.pluspet.core.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import br.com.pluspet.core.enums.AppointmentType;
import br.com.pluspet.core.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "type")
	@NotNull(message = "Tipo de serviço {jakarta.validation.constraints.NotNull.message}")
	@Enumerated(EnumType.STRING)
	private AppointmentType appointmentType;

	@Column(name = "date")
	@NotNull(message = "Data de atendimento {jakarta.validation.constraints.NotNull.message}")
	private LocalDate date;

	@Column(name = "additional_details")
	private String additionalDetails;

	@Column(name = "comment")
	private String comment;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "pet")
	@NotNull(message = "Pet {jakarta.validation.constraints.NotNull.message}")
	private Pet pet;

	@Transient
	private Status status;

}
