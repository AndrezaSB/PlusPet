package br.com.pluspet.core.enums;

import java.util.ArrayList;
import java.util.List;

public enum AnimalGender {

	MALE("Masculino"), FEMALE("Feminino");

	private String description;

	private AnimalGender(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AnimalGender fromDescription(String description) {
		for (AnimalGender status : values()) {
			if (status.description.equals(description)) {
				return status;
			}
		}
		return null;
	}

	public static List<String> getAllTypes() {
		List<String> types = new ArrayList<String>();

		for (AnimalGender type : values()) {
			types.add(type.description);
		}

		return types;
	}

}
