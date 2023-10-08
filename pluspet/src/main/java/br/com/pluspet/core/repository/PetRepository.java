package br.com.pluspet.core.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.pluspet.core.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, UUID> {

	public Optional<Pet> findById(UUID petId);

	public List<Pet> findByArchivedFalse();

	public Optional<Pet> findByIdAndArchivedFalse(UUID petId);

	public List<Pet> findByTutorId(UUID tutorId);

	@Query(value = "SELECT p.* FROM pet p LEFT JOIN tutor t ON t.id = p.tutor "
			+ "WHERE (?1 IS NULL OR p.archived = COALESCE(?1, NULL)) "
			+ "AND (LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(?2, '%'), '%'))) "
			+ "AND (LOWER(p.spieces) LIKE LOWER(CONCAT('%', COALESCE(?3, '%'), '%'))) "
			+ "AND (?4 IS NULL OR p.sex = COALESCE(?4, '')) "
			+ "AND (?5 IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', COALESCE(?5, '%'), '%'))) "
			+ "ORDER BY ?#{#pageable}", countQuery = "SELECT count(*) FROM pet p LEFT JOIN tutor t ON t.id = p.tutor "
					+ "WHERE (?1 IS NULL OR p.archived = COALESCE(?1, NULL)) "
					+ "AND (LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(?2, '%'), '%'))) "
					+ "AND (LOWER(p.spieces) LIKE LOWER(CONCAT('%', COALESCE(?3, '%'), '%'))) "
					+ "AND (?4 IS NULL OR p.sex = COALESCE(?4, '')) "
					+ "AND (?5 IS NULL OR LOWER(t.name) LIKE LOWER(CONCAT('%', COALESCE(?5, '%'), '%')))", nativeQuery = true)
	public Page<Pet> findAllByFilter(Boolean archived, String name, String spieces, String sex, String tutorName,
			Pageable pageable);

}
