package br.com.pluspet.core.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pluspet.core.entity.Pet;
import br.com.pluspet.core.repository.PetRepository;
import br.com.pluspet.core.vo.PetFilter;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PetService extends AbstractService<Pet, UUID, PetRepository> {

	public Optional<Pet> findById(UUID petId) {
		return repository.findById(petId);
	}

	public Optional<Pet> findByIdActive(UUID petId) {
		return repository.findByIdAndArchivedFalse(petId);
	}

	public Page<Pet> findActives(PetFilter filter, Pageable pageable) {
		return repository.findAllByFilter(false, filter.getName(), filter.getSpieces(), filter.getGenderName(),
				filter.getTutorName(), pageable);
	}

	public Pet savePet(Pet pet) {
		return repository.save(pet);
	}

	public List<Pet> finByTutorId(UUID tutorId) {
		return repository.findByTutorId(tutorId);
	}

	public List<Pet> archivePetsByTutor(UUID tutorId) {
		List<Pet> pets = this.finByTutorId(tutorId);

		if (!pets.isEmpty()) {
			for (Pet pet : pets) {
				pet.setArchived(true);
			}
		} else {
			return null;
		}

		return repository.saveAll(pets);

	}

}
