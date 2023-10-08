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
import br.com.pluspet.core.vo.PetFilter;
import br.com.pluspet.v1.dto.Pet;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/pet")
public class PetController {

	@Autowired
	private PetService service;

	@Autowired
	private TutorService tutorService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<Pet>> listAll() {
		List<Pet> pets = Optional.ofNullable(service.findAll()).orElseGet(Collections::emptyList).stream()
				.map(pet -> mapper.map(pet, Pet.class)).collect(toList());

		if (pets.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return ResponseEntity.ok(pets);
		}

	}

	@GetMapping("/active")
	public ResponseEntity<Page<Pet>> listAllActive(@RequestParam(required = false) String name,
			@RequestParam(required = false) String tutorName, @RequestParam(required = false) String spieces,
			@RequestParam(required = false) String gender, @PageableDefault(size = 10, page = 0, sort = {
					"name" }, direction = Sort.Direction.ASC) Pageable pageable) {

		return ResponseEntity.ok(service.findActives(
				PetFilter.builder().name(name).tutorName(tutorName).spieces(spieces).gender(gender).build(), pageable)
				.map(pet -> mapper.map(pet, Pet.class)));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pet> findPet(@PathVariable("id") UUID petId) {
		Pet pet = mapper.map(service.findById(petId), Pet.class);

		return Optional.ofNullable(pet).map(petResponse -> ResponseEntity.ok(petResponse))
				.orElseThrow(EntityNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<Pet> savePet(@RequestBody @Valid Pet pet) {

		br.com.pluspet.core.entity.Pet savePet = mapper.map(pet, br.com.pluspet.core.entity.Pet.class);

		savePet.setTutor(Optional.ofNullable(tutorService.findById(pet.getTutor().getId()))
				.map(tutorEntity -> tutorEntity.get()).orElseThrow(EntityNotFoundException::new));

		br.com.pluspet.core.entity.Pet test = service.savePet(savePet);

		Pet savedDTO = mapper.map(test, Pet.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedDTO);

	}

	@PutMapping("/active/{id}")
	public ResponseEntity<Pet> archivePet(@PathVariable("id") UUID petId) {
		Optional<br.com.pluspet.core.entity.Pet> petEntity = service.findById(petId);

		if (petEntity.isPresent()) {
			petEntity.get().setArchived(true);
		} else {
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(mapper.map(service.savePet(petEntity.get()), Pet.class));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pet> editTutor(@PathVariable("id") UUID petId, @RequestBody @Valid Pet pet) {
		Optional<br.com.pluspet.core.entity.Pet> petEntity = service.findByIdActive(petId);

		if (petEntity.isPresent()) {
			petEntity.get().setTutor(Optional.ofNullable(tutorService.findById(pet.getTutor().getId()))
					.map(tutorEntity -> tutorEntity.get()).orElseThrow(EntityNotFoundException::new));
			petEntity.get().setBirthDate(pet.getBirthDate());
			petEntity.get().setBreed(pet.getBreed());
			petEntity.get().setName(pet.getName());
			petEntity.get().setSpieces(null);
			petEntity.get().setGender(pet.getGender());

		} else {
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(mapper.map(service.savePet(petEntity.get()), Pet.class));
	}

}
