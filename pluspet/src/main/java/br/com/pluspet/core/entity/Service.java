package br.com.pluspet.core.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import br.com.pluspet.core.enums.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "service")
@Data
public class Service {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "type")
	@NotBlank(message = "Tipo de serviço {jakarta.validation.constraints.NotBlank.message}")
	private String type;

	@Column(name = "date")
	@NotNull(message = "Data de atendimento {jakarta.validation.constraints.@NotNull.message}")
	private LocalDate date;

	@Column(name = "additional_details")
	private String additionalDetails;

	@Column(name = "comment")
	private String comment;

	@OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<StatusHistory> statusHistory = new ArrayList<StatusHistory>();

	@Transient
	private Status actualStatus;

	@PostLoad
	private void fillTransientStatus() {
		if (statusHistory.size() > 0) {
			this.actualStatus = Status.fromCode(statusHistory.get(statusHistory.size() - 1).getStatusValue());
		}
	}
}
