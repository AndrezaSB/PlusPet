package br.com.pluspet.core.vo;

import java.time.LocalDate;
import java.util.List;

import br.com.pluspet.core.enums.AppointmentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppointmentFilter {

	private String petName;
	private String tutorName;
	private String appointmentType;
	private List<String> allowedTypes;
	private LocalDate date;

	public String getType() {

		AppointmentType type = AppointmentType.fromDescription(appointmentType);

		if (type != null) {
			return type.name();
		}

		return null;
	}

}
