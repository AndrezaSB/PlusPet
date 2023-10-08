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

	@Query(value = "SELECT t.* FROM tutor t WHERE t.archived = ?1 ORDER BY ?#{#pageable}", countQuery = "SELECT count(*) FROM tutor t WHERE t.archived = ?1", nativeQuery = true)
	public Page<Tutor> findByArchived(Boolean archived, Pageable pageable);

}
