package br.com.pluspet.core.vo;

import br.com.pluspet.core.enums.AnimalSex;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetFilter {

	private String name;
	private String tutorName;
	private String spieces;
	private String sex;

	public String getSexName() {
		AnimalSex sexName = AnimalSex.fromDescription(this.sex);

		if (sexName != null) {
			return sexName.name();
		}

		return null;
	}
}
