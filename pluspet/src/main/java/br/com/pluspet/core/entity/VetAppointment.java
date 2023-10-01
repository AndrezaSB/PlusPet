package br.com.pluspet.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "vet_appointment")
@Data
@EqualsAndHashCode(callSuper = true)
public class VetAppointment extends Service {

	@ManyToOne
	@JoinColumn(name = "veterinarian")
	@NotNull(message = "Veterinário {jakarta.validation.constraints.NotNull.message}")
	private Veterinarian veterinarian;

}
