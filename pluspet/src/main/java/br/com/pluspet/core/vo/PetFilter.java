package br.com.pluspet.core.vo;

import br.com.pluspet.core.enums.AnimalGender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetFilter {

	private String name;
	private String tutorName;
	private String spieces;
	private String gender;

	public String getGenderName() {
		AnimalGender genderName = AnimalGender.fromDescription(this.gender);

		if (genderName != null) {
			return genderName.name();
		}

		return null;
	}
}
