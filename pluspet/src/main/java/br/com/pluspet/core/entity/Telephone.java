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
@Table(name = "telephone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telephone {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "description")
	private String description;

	@Column(name = "phone_number")
	@NotBlank(message = "Número do telefone {jakarta.validation.constraints.NotBlank.message}")
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "tutor")
	@NotNull(message = "Tutor {jakarta.validation.constraints.NotNull.message}")
	private Tutor tutor;
}
