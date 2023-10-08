package br.com.pluspet.core.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import br.com.pluspet.core.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "status_history")
@Data
public class StatusHistory {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "start_date")
	@NotNull(message = "Data inicial {jakarta.validation.constraints.NotNull.message}")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	@NotNull(message = "Data final {jakarta.validation.constraints.NotNull.message}")
	private LocalDateTime endDate;

	@ManyToOne
	@JoinColumn(name = "employee")
	@NotNull(message = "Responsável {jakarta.validation.constraints.NotNull.message}")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "appointment")
	@NotNull(message = "Atendimento {jakarta.validation.constraints.NotNull.message}")
	private Appointment appointment;

	@Column(name = "status")
	@NotNull(message = "Status {jakarta.validation.constraints.NotNull.message}")
	private Integer status;

	@Transient
	private Status currentStatus;

	@PostLoad
	private void fillTransientStatus() {
		if (status > 0) {
			this.currentStatus = Status.fromCode(status);
		}
	}

	@PrePersist
	private void fillPersistentStatusValue() {
		if (currentStatus != null) {
			this.status = currentStatus.getCode();
		} else {
			this.status = null;
		}
	}

}
