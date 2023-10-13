package br.com.pluspet.core.enums;

public enum Role {

	ADMIN("Admin"), PET_CARE("Pet Care"), VETERINARIAN("Veterinarian"), ATTENDANT("Atendente");

	private String role;

	private Role(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public static Role fromDescription(String role) {
		for (Role roleEnum : values()) {
			if (roleEnum.role.equals(role)) {
				return roleEnum;
			}
		}
		return null;
	}

}
