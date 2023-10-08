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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pluspet.core.service.PetService;
import br.com.pluspet.core.service.TutorService;
import br.com.pluspet.core.vo.TutorFilter;
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
	private PetService petService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<TutorBasicInfo>> listAll() {
		List<TutorBasicInfo> tutors = Optional.ofNullable(tutorService.findAll()).orElseGet(Collections::emptyList)
				.stream().map(tutor -> mapper.map(tutor, TutorBasicInfo.class)).collect(toList());

		if (tutors.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return ResponseEntity.ok(tutors);
		}
	}

	@GetMapping("/active")
	public ResponseEntity<Page<TutorBasicInfo>> listAllActive(@RequestParam(required = false) String name,
			@RequestParam(required = false) String cpf, @RequestParam(required = false) String email,
			@PageableDefault(size = 10, page = 0, sort = {
					"name" }, direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity
				.ok(tutorService.findActives(TutorFilter.builder().name(name).cpf(cpf).email(email).build(), pageable)
						.map(tutor -> mapper.map(tutor, TutorBasicInfo.class)));

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

		br.com.pluspet.core.entity.Tutor tutorEntity = mapper.map(tutor, br.com.pluspet.core.entity.Tutor.class);
		tutorEntity.setId(tutorId);

		Tutor updatedTutor = mapper.map(tutorService.updateTutor(tutorEntity), Tutor.class);

		return Optional.ofNullable(updatedTutor).map(tutorResponse -> ResponseEntity.ok(tutorResponse))
				.orElseThrow(EntityNotFoundException::new);
	}

}
