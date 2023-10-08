package br.com.pluspet.core.enums;

import java.util.ArrayList;
import java.util.List;

public enum AnimalSex {

	MALE("Masculino"), FEMALE("Feminino");

	private String description;

	private AnimalSex(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static AnimalSex fromDescription(String description) {
		for (AnimalSex status : values()) {
			if (status.description.equals(description)) {
				return status;
			}
		}
		return null;
	}

	public static List<String> getAllTypes() {
		List<String> types = new ArrayList<String>();

		for (AnimalSex type : values()) {
			types.add(type.description);
		}

		return types;
	}

}
