package br.com.pluspet.core.enums;

import java.util.ArrayList;
import java.util.List;

public enum AppointmentType {

	BATH("Banho"), BATH_GROOMING("Banho & Tosa"), MEDICAL_APPOINTMENT("Consulta Veterinária"),
	MEDICAL_RETURN("Retorno consulta");

	private String description;

	private AppointmentType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AppointmentType fromDescription(String description) {
		for (AppointmentType status : values()) {
			if (status.description.equals(description)) {
				return status;
			}
		}
		return null;
	}

	public static List<String> getAllTypes() {
		List<String> types = new ArrayList<String>();

		for (AppointmentType type : values()) {
			types.add(type.description);
		}

		return types;
	}

}
