package br.com.pluspet.core.util;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import br.com.pluspet.core.enums.AppointmentType;
import br.com.pluspet.core.enums.Role;

public class AppointmentTypeUtil {

	public static List<AppointmentType> getAllowedAppointmentsTypes(Role role) {

		List<AppointmentType> allowedAppointmentTypes = null;

		if (Role.PET_CARE.equals(role)) {
			allowedAppointmentTypes = Arrays.asList(AppointmentType.BATH, AppointmentType.BATH_GROOMING);
		} else if (Role.VETERINARIAN.equals(role)) {
			allowedAppointmentTypes = Arrays.asList(AppointmentType.MEDICAL_APPOINTMENT,
					AppointmentType.MEDICAL_RETURN);
		}

		return Optional.ofNullable(allowedAppointmentTypes).orElse(Arrays.asList(AppointmentType.values()));
	}
}
