package br.com.pluspet.core.entity;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.pluspet.core.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3651758974013846646L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", columnDefinition = "uuid", insertable = true, updatable = false, nullable = false)
	private UUID id;

	@Column(name = "name")
	@NotBlank(message = "Nome {jakarta.validation.constraints.NotBlank.message}")
	private String name;

	@Column(name = "login", updatable = false, nullable = false, unique = true)
	@NotBlank(message = "Login {jakarta.validation.constraints.NotBlank.message}")
	private String login;

	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password {jakarta.validation.constraints.NotBlank.message}")
	private String password;

	@Column(name = "crmv")
	private String CRMV;

	@Column(name = "role", nullable = false)
	@NotNull(message = "Role {jakarta.validation.constraints.NotNull.message}")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == Role.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_VET"),
					new SimpleGrantedAuthority("ROLE_ATTENDANT"), new SimpleGrantedAuthority("ROLE_PET_CARE"));
		} else if (this.role == Role.ATTENDANT) {
			return List.of(new SimpleGrantedAuthority("ROLE_ATTENDANT"));
		} else if (this.role == Role.VETERINARIAN) {
			return List.of(new SimpleGrantedAuthority("ROLE_VET"));
		} else if (this.role == Role.PET_CARE) {
			return List.of(new SimpleGrantedAuthority("ROLE_PET_CARE"));
		}

		return List.of();
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
