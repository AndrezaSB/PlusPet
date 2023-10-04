package br.com.pluspet.v1.controller;

import static java.util.stream.Collectors.toList;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pluspet.core.entity.Address;
import br.com.pluspet.core.entity.Pet;
import br.com.pluspet.core.entity.Telephone;
import br.com.pluspet.core.entity.Tutor;
import br.com.pluspet.core.service.PetService;
import br.com.pluspet.core.service.TutorService;
import br.com.pluspet.v1.dto.TutorRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/tutor")
public class TutorController {

	@Autowired
	private TutorService tutorService;

	@Autowired
	private PetService petService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<TutorRequest>> listAll() {

		List<TutorRequest> tutors = Optional.ofNullable(tutorService.findAll()).orElseGet(Collections::emptyList)
				.stream().map(tutor -> mapper.map(tutor, TutorRequest.class)).collect(toList());

		mapper.map(tutorService.findAll(), TutorRequest.class);

		if (tutors.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return ResponseEntity.ok(tutors);
		}

	}

	@GetMapping("/active")
	public ResponseEntity<List<TutorRequest>> listAllActive() {

		List<TutorRequest> tutors = Optional.ofNullable(tutorService.findActives()).orElseGet(Collections::emptyList)
				.stream().map(tutor -> mapper.map(tutor, TutorRequest.class)).collect(toList());

		if (tutors.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return ResponseEntity.ok(tutors);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<TutorRequest> findTutor(@PathVariable("id") UUID tutorId) {
		TutorRequest tutor = mapper.map(tutorService.findById(tutorId), TutorRequest.class);

		return Optional.ofNullable(tutor).map(tutorResponse -> ResponseEntity.ok(tutorResponse))
				.orElseThrow(EntityNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<TutorRequest> saveTutor(@RequestBody @Valid TutorRequest tutor) {
		Tutor savedTutorEntity = tutorService.saveTutor(mapper.map(tutor, Tutor.class));

		TutorRequest savedDTO = mapper.map(savedTutorEntity, TutorRequest.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedDTO);

	}

	@PutMapping("/active/{id}")
	public ResponseEntity<TutorRequest> archiveTutor(@PathVariable("id") UUID tutorId) {
		Tutor tutor = tutorService.archiveTutor(tutorId);

		if (tutor != null) {
			petService.archivePetsByTutor(tutorId);
		} else {
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(mapper.map(tutorService.saveTutor(tutor), TutorRequest.class));
	}

//	@PutMapping("/{id}")
//	public ResponseEntity<Tutor> editTutor(@PathVariable("id") UUID tutorId, @RequestBody @Valid Tutor tutor) {
//		Optional<Tutor> tutorEntity = service.findByIdActive(tutorId);
//
//		if (tutorEntity.isPresent()) {
//
////			tutor.setId(tutorEntity.get().getId());
////			tutor.setArchived(tutorEntity.get().getArchived());
//
//			tutorEntity.get().setBirthDate(tutor.getBirthDate());
//			tutorEntity.get().setCpf(tutor.getCpf());
//			tutorEntity.get().setEmail(tutor.getEmail());
//			tutorEntity.get().setName(tutor.getName());
//
//			List<Address> addressesEntities = Optional.ofNullable(tutor.getAddresses())
//					.orElseGet(Collections::emptyList).stream().map(address -> mapper.map(address, Address.class))
//					.collect(toList());
//
//			for (Address address : addressesEntities) {
//				address.setTutor(tutorEntity.get());
//			}
//
//			tutorEntity.get().setAddresses(addressesEntities);
//
//			List<Telephone> telephonesEntities = Optional.ofNullable(tutor.getTelephones())
//					.orElseGet(Collections::emptyList).stream().map(telephone -> mapper.map(telephone, Telephone.class))
//					.collect(toList());
//			tutorEntity.get().setTelephones(telephonesEntities);
//		} else {
//			throw new EntityNotFoundException();
//		}
//
////		Tutor savedTutor = ervice.saveTutor(mapper.map(tutor, Tutor.class));
//
//		return ResponseEntity.ok(mapper.map(service.saveTutor(tutorEntity.get()), Tutor.class));
//	}

}
