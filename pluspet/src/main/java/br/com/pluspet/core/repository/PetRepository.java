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

	@Query(value = "SELECT p.* FROM pet p WHERE p.archived = ?1 ORDER BY ?#{#pageable}", countQuery = "SELECT count(*) FROM pet p WHERE p.archived = ?1", nativeQuery = true)
	public Page<Pet> findByArchived(Boolean archived, Pageable pageable);
}
