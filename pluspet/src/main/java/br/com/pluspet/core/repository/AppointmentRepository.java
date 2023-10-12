package br.com.pluspet.core.repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pluspet.core.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

//	@Query(value = "SELECT a.*, (SELECT s.status FROM status_history s WHERE s.appointment) FROM appointment a WHERE a.id = ?1")

//	@Query("SELECT a, s.status FROM appointment a "
//			+ "LEFT JOIN status_history s ON a.id = s.appointment AND a.id = ?1 "
//			+ "ORDER BY s.id DESC FETCH FIRST 1 ROW ONLY")
	public Optional<Appointment> findById(UUID appointmentId);
//
//	@Query("SELECT s.status FROM appointment a " + "LEFT JOIN status_history s ON a.id = s.appointment AND a.id = ?1 "
//			+ "ORDER BY s.id DESC FETCH FIRST 1 ROW ONLY")
//	public Status findStatus(UUID appointmentId);

	@Query(value = "SELECT a.* FROM appointment a LEFT JOIN pet p ON p.id = a.pet "
			+ "LEFT JOIN tutor t ON t.id = p.tutor WHERE a.date = ?1 AND (?2 IS NULL OR a.type LIKE ?2) "
			+ "AND (LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(?3, '%'), '%'))) "
			+ "AND (LOWER(T.name) LIKE LOWER(CONCAT('%', COALESCE(?4, '%'), '%'))) "
			+ "ORDER BY ?#{#pageable}", countQuery = "SELECT a.* FROM appointment a LEFT JOIN pet p ON p.id = a.pet "
					+ "LEFT JOIN tutor t ON t.id = p.tutor WHERE a.date = ?1 AND (?2 IS NULL OR a.type LIKE ?2) "
					+ "AND (LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(?3, '%'), '%'))) "
					+ "AND (LOWER(T.name) LIKE LOWER(CONCAT('%', COALESCE(?4, '%'), '%'))) ", nativeQuery = true)
	public Page<Appointment> findAllByFilter(LocalDate date, String type, String petName, String tutorName,
			Pageable pageable);

//	SELECT a.*, s.status FROM appointment a 
//	LEFT JOIN status_history s ON s.appointment = a.id AND a.id= '311aa9d2-ef39-49ec-95b4-9f9f2884580f' 
//	
//	ORDER BY s.id DESC FETCH FIRST 1 ROW ONLY; 

//	@Query(value = "SELECT a.* FROM appointment a LEFT JOIN pet p ON p.id = a.pet LEFT JOIN tutor t ON t.pet = p.id ORDER BY ?#{#pageable}", countQuery = "SELECT COUNT(*) FROM appointment a LEFT JOIN pet p ON p.id = a.pet LEFT JOIN tutor t ON t.pet = p.id", nativeQuery = true)
//	public Page<Appointment> findAllByFilter(LocalDate date, String type, String petName, String tutorName,
//			Pageable pageable);

}
