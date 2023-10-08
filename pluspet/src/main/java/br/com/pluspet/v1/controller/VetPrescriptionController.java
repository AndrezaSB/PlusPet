package br.com.pluspet.v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/")
public class VetPrescriptionController {
//
//	@Autowired
//	private TutorService service;
//
//	@Autowired
//	private ModelMapper mapper;
//
//	@GetMapping
//	public ResponseEntity<List<Tutor>> listAll() {
//
//		List<Tutor> tutors = Optional.ofNullable(service.findAll()).orElseGet(Collections::emptyList).stream()
//				.map(tutor -> mapper.map(tutor, Tutor.class)).collect(toList());
//
//		mapper.map(service.findAll(), Tutor.class);
//
//		if (tutors.isEmpty()) {
//			throw new EntityNotFoundException();
//		} else {
//			return ResponseEntity.ok(tutors);
//		}
//
//	}
//
//	@GetMapping("/active")
//	public ResponseEntity<List<Tutor>> listAllActive() {
//
//		List<Tutor> tutors = Optional.ofNullable(service.findActives()).orElseGet(Collections::emptyList).stream()
//				.map(tutor -> mapper.map(tutor, Tutor.class)).collect(toList());
//
//		mapper.map(service.findAll(), Tutor.class);
//
//		if (tutors.isEmpty()) {
//			throw new EntityNotFoundException();
//		} else {
//			return ResponseEntity.ok(tutors);
//		}
//
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Tutor> findTutor(@PathVariable("id") UUID tutorId) {
//		Tutor tutor = mapper.map(service.findById(tutorId), Tutor.class);
//
//		return Optional.ofNullable(tutor).map(tutorResponse -> ResponseEntity.ok(tutorResponse))
//				.orElseThrow(EntityNotFoundException::new);
//	}
//
//	@PostMapping
//	public ResponseEntity<Tutor> saveTutor(@RequestBody @Valid Tutor tutor) {
//		br.com.pluspet.core.entity.Tutor savedTutorEntity = service
//				.saveTutor(mapper.map(tutor, br.com.pluspet.core.entity.Tutor.class));
//
//		Tutor savedDTO = mapper.map(savedTutorEntity, Tutor.class);
//
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDTO.getId())
//				.toUri();
//
//		return ResponseEntity.created(location).body(savedDTO);
//
//	}
//
//	@PutMapping("/archive/{id}")
//	public ResponseEntity<Tutor> archiveTutor(@PathVariable("id") UUID tutorId) {
//		Optional<br.com.pluspet.core.entity.Tutor> tutorEntity = service.findById(tutorId);
//
//		if (tutorEntity.isPresent()) {
//			tutorEntity.get().setArchived(true);
//		} else {
//			throw new EntityNotFoundException();
//		}
//
//		return ResponseEntity.ok(mapper.map(service.saveTutor(tutorEntity.get()), Tutor.class));
//	}

}
