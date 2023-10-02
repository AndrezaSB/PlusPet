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

import br.com.pluspet.core.service.PetService;
import br.com.pluspet.v1.dto.Pet;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/pet")
public class PetController {

	@Autowired
	private PetService service;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<Pet>> listAll() {

		List<Pet> pets = Optional.ofNullable(service.findAll()).orElseGet(Collections::emptyList).stream()
				.map(pet -> mapper.map(pet, Pet.class)).collect(toList());

		mapper.map(service.findAll(), Pet.class);

		if (pets.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return ResponseEntity.ok(pets);
		}

	}

	@GetMapping("/active")
	public ResponseEntity<List<Pet>> listAllActive() {

		List<Pet> pets = Optional.ofNullable(service.findActives()).orElseGet(Collections::emptyList).stream()
				.map(pet -> mapper.map(pet, Pet.class)).collect(toList());

		mapper.map(service.findAll(), Pet.class);

		if (pets.isEmpty()) {
			throw new EntityNotFoundException();
		} else {
			return ResponseEntity.ok(pets);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<Pet> findPet(@PathVariable("id") UUID petId) {
		Pet pet = mapper.map(service.findById(petId), Pet.class);

		return Optional.ofNullable(pet).map(petResponse -> ResponseEntity.ok(petResponse))
				.orElseThrow(EntityNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<Pet> savePet(@RequestBody @Valid Pet pet) {
		br.com.pluspet.core.entity.Pet savedPetEntity = service
				.savePet(mapper.map(pet, br.com.pluspet.core.entity.Pet.class));

		Pet savedDTO = mapper.map(savedPetEntity, Pet.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedDTO);

	}

	@PutMapping("/archive/{id}")
	public ResponseEntity<Pet> archivePet(@PathVariable("id") UUID petId) {
		Optional<br.com.pluspet.core.entity.Pet> petEntity = service.findById(petId);

		if (petEntity.isPresent()) {
			petEntity.get().setArchived(true);
		} else {
			throw new EntityNotFoundException();
		}

		return ResponseEntity.ok(mapper.map(service.savePet(petEntity.get()), Pet.class));
	}

}
