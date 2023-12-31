package br.com.pluspet.core.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pluspet.core.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

	public Optional<Appointment> findById(UUID appointmentId);

	@Query(value = "SELECT a.* FROM appointment a LEFT JOIN pet p ON p.id = a.pet "
			+ "LEFT JOIN tutor t ON t.id = p.tutor WHERE a.date = ?1 AND (?2 IS NULL OR a.type LIKE ?2) "
			+ "AND (a.type IN ?3) AND (LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(?4, '%'), '%'))) "
			+ "AND (LOWER(t.name) LIKE LOWER(CONCAT('%', COALESCE(?5, '%'), '%'))) "
			+ "ORDER BY ?#{#pageable}", countQuery = "SELECT a.* FROM appointment a LEFT JOIN pet p ON p.id = a.pet "
					+ "LEFT JOIN tutor t ON t.id = p.tutor WHERE a.date = ?1 AND (?2 IS NULL OR a.type LIKE ?2) "
					+ "AND (a.type IN ?3) AND (LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(?4, '%'), '%'))) "
					+ "AND (LOWER(t.name) LIKE LOWER(CONCAT('%', COALESCE(?5, '%'), '%'))) ", nativeQuery = true)
	public Page<Appointment> findAllByFilter(LocalDate date, String type, List<String> allowedtypes, String petName,
			String tutorName, Pageable pageable);

}
