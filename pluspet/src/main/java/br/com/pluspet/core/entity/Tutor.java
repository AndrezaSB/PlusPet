package br.com.pluspet.core.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tutor")
@Data
public class Tutor {

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

	@Column(name = "cpf")
	@NotBlank(message = "CPF {jakarta.validation.constraints.NotBlank.message}")
	private String cpf;

	@Column(name = "email")
	@NotBlank(message = "E-mail {jakarta.validation.constraints.NotBlank.message}")
	private String email;

	@Column(name = "archived")
	@NotNull(message = "Tutor ativo {jakarta.validation.constraints.NotNull.message}")
	private Boolean archived = false;

	@NotEmpty(message = "Endereço {jakarta.validation.constraints.NotEmpty.message}")
	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<Address>();

	@NotEmpty(message = "Telefone {jakarta.validation.constraints.NotEmpty.message}")
	@OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
	private List<@Valid Telephone> telephones;

	@PrePersist
	public void prepareDependenciesPersist() {
		for (Address address : addresses) {
			address.setTutor(this);
		}

		for (Telephone telephone : telephones) {
			telephone.setTutor(this);
		}
	}

	@PreUpdate
	public void prepareDependenciesUpdate() {
		for (Address address : addresses) {
			address.setTutor(this);
		}

		for (Telephone telephone : telephones) {
			telephone.setTutor(this);
		}
	}
}