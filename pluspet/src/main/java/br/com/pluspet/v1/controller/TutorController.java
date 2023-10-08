package br.com.pluspet.v1.controller;

import static java.util.stream.Collectors.toList;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import br.com.pluspet.core.entity.Telephone;
import br.com.pluspet.core.repository.TutorRepository;
import br.com.pluspet.core.service.PetService;
import br.com.pluspet.core.service.TutorService;
import br.com.pluspet.v1.dto.Tutor;
import br.com.pluspet.v1.dto.TutorBasicInfo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/tutor")
public class TutorController {

	@Autowired
	private TutorService tutorService;

	@Autowired
	private TutorRepository repository;

	@Autowired
	private PetService petService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<Page<TutorBasicInfo>> listAll(@PageableDefault(size = 10, page = 0, sort = {
			"name" }, direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.ok(tutorService.findAll(pageable).map(tutor -> mapper.map(tutor, TutorBasicInfo.class)));

	}

	@GetMapping("/active")
	public ResponseEntity<Page<TutorBasicInfo>> listAllActive(@PageableDefault(size = 10, page = 0, sort = {
			"name" }, direction = Sort.Direction.ASC) Pageable pageable) {

		return ResponseEntity
				.ok(tutorService.findActives(pageable).map(tutor -> mapper.map(tutor, TutorBasicInfo.class)));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Tutor> findTutor(@PathVariable("id") UUID tutorId) {
		Tutor tutor = mapper.map(tutorService.findById(tutorId), Tutor.class);

		return Optional.ofNullable(tutor).map(tutorResponse -> ResponseEntity.ok(tutorResponse))
				.orElseThrow(EntityNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<Tutor> saveTutor(@Valid @RequestBody Tutor tutor) {
		br.com.pluspet.core.entity.Tutor savedTutorEntity = tutorService
				.saveTutor(mapper.map(tutor, br.com.pluspet.core.entity.Tutor.class));

		Tutor savedDTO = mapper.map(savedTutorEntity, Tutor.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedDTO);

	}

	@PutMapping("/active/{id}")
	public ResponseEntity<Tutor> archiveTutor(@PathVariable("id") UUID tutorId) {
		br.com.pluspet.core.entity.Tutor tutor = tutorService.archiveTutor(tutorId);

		if (tutor != null) {
			petService.archivePetsByTutor(tutorId);
		} else {
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(mapper.map(tutorService.saveTutor(tutor), Tutor.class));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tutor> editTutor(@PathVariable("id") UUID tutorId, @Valid @RequestBody Tutor tutor) {
		Optional<br.com.pluspet.core.entity.Tutor> tutorEntity = tutorService.findByIdActive(tutorId);

		if (tutorEntity.isPresent()) {

			tutorEntity.get().setBirthDate(tutor.getBirthDate());
			tutorEntity.get().setCpf(tutor.getCpf());
			tutorEntity.get().setEmail(tutor.getEmail());
			tutorEntity.get().setName(tutor.getName());

			List<Address> addressesEntities = Optional.ofNullable(tutor.getAddresses())
					.orElseGet(Collections::emptyList).stream().map(address -> mapper.map(address, Address.class))
					.collect(toList());

			for (Address address : addressesEntities) {
				address.setTutor(tutorEntity.get());
			}
			tutorEntity.get().getAddresses().clear();

			tutorEntity.get().getAddresses().addAll(addressesEntities);

			List<Telephone> telephonesEntities = Optional.ofNullable(tutor.getTelephones())
					.orElseGet(Collections::emptyList).stream().map(telephone -> mapper.map(telephone, Telephone.class))
					.collect(toList());

			for (Telephone telephone : telephonesEntities) {
				telephone.setTutor(tutorEntity.get());
			}

			tutorEntity.get().getTelephones().clear();

			tutorEntity.get().getTelephones().addAll(telephonesEntities);
		} else {
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(mapper.map(tutorService.saveTutor(tutorEntity.get()), Tutor.class));
	}

}
