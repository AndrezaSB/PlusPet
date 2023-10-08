package br.com.pluspet.core.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.pluspet.core.entity.Address;
import br.com.pluspet.core.entity.Telephone;
import br.com.pluspet.core.entity.Tutor;
import br.com.pluspet.core.repository.TutorRepository;

@Service
public class TutorService extends AbstractService<Tutor, UUID, TutorRepository> {

	public Optional<Tutor> findById(UUID tutorId) {
		return repository.findById(tutorId);
	}

	public Optional<Tutor> findByIdActive(UUID tutorId) {
		return repository.findByIdAndArchivedFalse(tutorId);
	}

	public Page<Tutor> findActives(Pageable pageable) {
		return repository.findByArchived(false, pageable);
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

	public Tutor updateTutor(Tutor tutor) {
		Optional<br.com.pluspet.core.entity.Tutor> tutorEntity = repository.findByIdAndArchivedFalse(tutor.getId());

		if (tutorEntity != null && tutorEntity.isPresent()) {

			tutorEntity.get().setBirthDate(tutor.getBirthDate());
			tutorEntity.get().setCpf(tutor.getCpf());
			tutorEntity.get().setEmail(tutor.getEmail());
			tutorEntity.get().setName(tutor.getName());

			List<Address> addressesEntities = tutor.getAddresses();

			for (Address address : addressesEntities) {
				address.setTutor(tutorEntity.get());
			}
			tutorEntity.get().getAddresses().clear();

			tutorEntity.get().getAddresses().addAll(addressesEntities);

			List<Telephone> telephonesEntities = tutor.getTelephones();

			for (Telephone telephone : telephonesEntities) {
				telephone.setTutor(tutorEntity.get());
			}

			tutorEntity.get().getTelephones().clear();

			tutorEntity.get().getTelephones().addAll(telephonesEntities);

			return repository.save(tutorEntity.get());
		}

		return null;
	}
}
