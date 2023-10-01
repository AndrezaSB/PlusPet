package br.com.pluspet.core.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pluspet.core.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, UUID> {

	public Optional<Tutor> findById(UUID tutorId);

	public List<Tutor> findByArchivedFalse();

	public Optional<Tutor> findByIdAndArchivedFalse(UUID tutorId);
}
