package br.com.pluspet.core.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pluspet.core.entity.Appointment;
import br.com.pluspet.core.entity.StatusHistory;
import br.com.pluspet.core.enums.Status;
import br.com.pluspet.core.repository.StatusHistoryRepository;

@Service
public class StatusHistoryService extends AbstractService<StatusHistory, Integer, StatusHistoryRepository> {

	@Transactional(propagation = Propagation.REQUIRED)
	public StatusHistory updateStatusHistory(Appointment appointment, Status status) {

		StatusHistory statusHistory = new StatusHistory();

		if (statusHistory != null) {

			statusHistory.setDate(LocalDateTime.now());
			statusHistory.setStatus(status);

			if (appointment != null) {
				statusHistory.setAppointment(appointment);
			}
		}

		return repository.save(statusHistory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public StatusHistory saveStatusHistory(StatusHistory statusHistory) {
		return repository.save(statusHistory);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public StatusHistory createStatusHistory(Appointment appointment) {

		StatusHistory newStatus = new StatusHistory();
		newStatus.setAppointment(appointment);
		newStatus.setDate(LocalDateTime.now());
		newStatus.setStatus(Status.WAITING_SERVICE);

		return repository.save(newStatus);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Optional<StatusHistory> findAppointmentsActualStatus(UUID appointmentId) {
		return repository.findFirstByAppointmentIdOrderByIdDesc(appointmentId);
	}

}
