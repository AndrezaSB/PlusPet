package br.com.pluspet.core.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pluspet.core.entity.Appointment;
import br.com.pluspet.core.entity.Employee;
import br.com.pluspet.core.entity.StatusHistory;
import br.com.pluspet.core.enums.Status;
import br.com.pluspet.core.repository.AppointmentRepository;
import br.com.pluspet.core.util.AppointmentTypeUtil;
import br.com.pluspet.core.vo.AppointmentFilter;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AppointmentService extends AbstractService<Appointment, UUID, AppointmentRepository> {

	@Autowired
	protected StatusHistoryService statusService;

	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Appointment> findById(UUID appointmentId) {
		Optional<Appointment> appointment = repository.findById(appointmentId);

		if (appointment.isPresent()) {
			appointment.get().setStatus(getCurrentStatus(appointmentId));
		}

		return appointment;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Appointment saveAppointment(Appointment appointment) {
		return repository.save(appointment);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Appointment createAppointment(Appointment appointment, Employee employee) {

		Appointment savedAppointment = repository.save(appointment);
		savedAppointment.setStatus(statusService.createStatusHistory(savedAppointment, employee).getStatus());

		return savedAppointment;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Page<Appointment> findAll(AppointmentFilter filter, Pageable pageable) {
		Page<Appointment> appointments = repository.findAllByFilter(filter.getDate(), filter.getAppointmentType(),
				filter.getAllowedTypes(), filter.getPetName(), filter.getTutorName(), pageable);

		if (appointments != null) {
			for (Appointment appointment : appointments) {
				appointment.setStatus(getCurrentStatus(appointment.getId()));
			}
		}

		return appointments;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<Appointment> updateAppointmentStatusHistory(UUID appointmentId, Status status, Employee employee) {
		Optional<br.com.pluspet.core.entity.Appointment> appointment = repository.findById(appointmentId);

		List<String> allowedTypes = AppointmentTypeUtil.getAllowedAppointmentsTypes(employee.getRole()).stream()
				.map(type -> type.name()).collect(toList());

		if (appointment.isPresent()) {

			if (!allowedTypes.contains(appointment.get().getAppointmentType().name())) {
				throw new EntityNotFoundException();
			}

			appointment.get()
					.setStatus(statusService.updateStatusHistory(appointment.get(), status, employee).getStatus());
		}

		return appointment;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	private Status getCurrentStatus(UUID appointmentId) {
		Optional<StatusHistory> history = statusService.findAppointmentsActualStatus(appointmentId);

		if (!history.isPresent()) {
			throw new EntityNotFoundException();
		}

		return history.get().getStatus();

	}

}
