package br.com.pluspet.core.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pluspet.core.entity.Pet;

public interface PetRepository extends JpaRepository<Pet, UUID> {

	public Optional<Pet> findById(UUID petId);

	public List<Pet> findByArchivedFalse();

	public Optional<Pet> findByIdAndArchivedFalse(UUID petId);

	public List<Pet> findByTutorId(UUID tutorId);
}
