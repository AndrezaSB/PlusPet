package br.com.pluspet.core.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pluspet.core.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, UUID> {

	public Optional<Tutor> findById(UUID tutorId);

	public List<Tutor> findByArchivedFalse();

	public Optional<Tutor> findByIdAndArchivedFalse(UUID tutorId);

	@Query(value = "SELECT t.* FROM tutor t WHERE (?1 IS NULL OR t.archived = COALESCE(?1, NULL)) "
			+ "AND (LOWER(t.name) LIKE LOWER(CONCAT('%', COALESCE(?2, '%'), '%'))) AND (LOWER(t.cpf) LIKE LOWER(CONCAT('%', COALESCE(?3, '%'), '%'))) "
			+ "AND (LOWER(t.email) LIKE LOWER(CONCAT('%', COALESCE(?4, '%'), '%'))) "
			+ "ORDER BY ?#{#pageable}", countQuery = "SELECT count(*) FROM tutor t WHERE (?1 IS NULL OR t.archived = COALESCE(?1, NULL)) "
					+ "AND (LOWER(t.name) LIKE LOWER(CONCAT('%', COALESCE(?2, '%'), '%'))) AND (LOWER(t.cpf) LIKE LOWER(CONCAT('%', COALESCE(?3, '%'), '%'))) "
					+ "AND (LOWER(t.email) LIKE LOWER(CONCAT('%', COALESCE(?4, '%'), '%')))", nativeQuery = true)
	public Page<Tutor> findAllByFilter(Boolean archived, String name, String cpf, String email, Pageable pageable);

}
