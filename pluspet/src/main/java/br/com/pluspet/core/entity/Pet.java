package br.com.pluspet.core.entity;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import br.com.pluspet.core.enums.AnimalGender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "pet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "name")
	@NotBlank(message = "Nome {jakarta.validation.constraints.NotBlank.message}")
	private String name;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "spieces")
	@NotBlank(message = "Espécie {jakarta.validation.constraints.NotBlank.message}")
	private String spieces;

	@Column(name = "gender")
	@NotNull(message = "Sexo {jakarta.validation.constraints.NotNull.message}")
	@Enumerated(EnumType.STRING)
	private AnimalGender gender;

	@Column(name = "breed")
	@NotBlank(message = "Raça {jakarta.validation.constraints.NotBlank.message}")
	private String breed;

	@Column(name = "archived")
	@NotNull(message = "Tutor ativo {jakarta.validation.constraints.NotNull.message}")
	private Boolean archived = false;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "tutor")
	@NotNull(message = "Tutor {jakarta.validation.constraints.NotNull.message}")
	private Tutor tutor;

}
