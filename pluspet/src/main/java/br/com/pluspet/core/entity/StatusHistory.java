package br.com.pluspet.core.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import br.com.pluspet.core.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "status_history")
@Data
public class StatusHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", insertable = true, updatable = false, nullable = false)
	private Integer id;

	@Column(name = "date")
	@NotNull(message = "Data inicial {jakarta.validation.constraints.NotNull.message}")
	private LocalDateTime date;

	@ManyToOne
	@JoinColumn(name = "employee")
//	@NotNull(message = "Responsável {jakarta.validation.constraints.NotNull.message}")
	private Employee employee;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment")
	@NotNull(message = "Atendimento {jakarta.validation.constraints.NotNull.message}")
	private Appointment appointment;

	@Column(name = "status")
	@NotNull(message = "Status {jakarta.validation.constraints.NotNull.message}")
	@Enumerated(EnumType.STRING)
	private Status status;

}
