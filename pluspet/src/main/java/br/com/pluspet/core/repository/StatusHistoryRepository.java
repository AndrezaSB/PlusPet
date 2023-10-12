package br.com.pluspet.core.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pluspet.core.entity.StatusHistory;

public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer> {

	public Optional<StatusHistory> findFirstByAppointmentIdOrderByIdDesc(UUID appointmentId);

}
