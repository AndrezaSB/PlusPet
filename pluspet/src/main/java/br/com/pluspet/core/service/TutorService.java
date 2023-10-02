package br.com.pluspet.core.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.pluspet.core.entity.Tutor;
import br.com.pluspet.core.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TutorService extends AbstractService<Tutor, UUID, TutorRepository> {

	public Optional<Tutor> findById(UUID tutorId) {
		return repository.findById(tutorId);
	}

	public Optional<Tutor> findByIdActive(UUID tutorId) {
		return repository.findByIdAndArchivedFalse(tutorId);
	}

	public List<Tutor> findActives() {
		return repository.findByArchivedFalse();
	}

	public Tutor saveTutor(Tutor tutor) {
		return repository.save(tutor);
	}

	public Tutor archiveTutor(UUID tutorId) {
		Optional<br.com.pluspet.core.entity.Tutor> tutor = this.findById(tutorId);

		if (tutor.isPresent()) {
			tutor.get().setArchived(true);
		} else {
			return null;
		}

		return this.saveTutor(tutor.get());

	}
}
