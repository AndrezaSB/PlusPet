package br.com.pluspet.v1.controller;

import java.net.URI;
import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.pluspet.core.entity.Pet;
import br.com.pluspet.core.enums.AppointmentType;
import br.com.pluspet.core.service.AppointmentService;
import br.com.pluspet.core.service.PetService;
import br.com.pluspet.core.vo.AppointmentFilter;
import br.com.pluspet.v1.dto.Appointment;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	@Autowired
	private PetService petService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/type")
	public ResponseEntity<List<String>> listAppointmentType() {
		return ResponseEntity.ok(AppointmentType.getAllTypes());
	}

	@GetMapping("/")
	public ResponseEntity<Page<Appointment>> listAll(@RequestParam(required = false) String petName,
			@RequestParam(required = false) String tutorName, @RequestParam(required = false) String appointmentType,
			@RequestParam(required = true) LocalDate appointmentDate,
			@PageableDefault(size = 10, page = 0, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable) {

		if (appointmentDate == null) {
			appointmentDate = LocalDate.now();
		}

		AppointmentFilter filter = AppointmentFilter.builder().petName(petName).tutorName(tutorName)
				.appointmentType(appointmentType).date(appointmentDate).build();

		return ResponseEntity
				.ok(service.findAll(filter, pageable).map(appointment -> mapper.map(appointment, Appointment.class)));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Appointment> findAppointment(@PathVariable("id") UUID appointmentId) {
		Appointment appointment = mapper.map(service.findById(appointmentId), Appointment.class);

		return Optional.ofNullable(appointment).map(appointmentResponse -> ResponseEntity.ok(appointmentResponse))
				.orElseThrow(EntityNotFoundException::new);
	}

	@PostMapping
	public ResponseEntity<Appointment> saveAppointment(@Valid @RequestBody Appointment appointment) {

		Pet petEntity = Optional.ofNullable(petService.findByIdActive(appointment.getPet().getId()))
				.map(pet -> pet.get()).orElseThrow(EntityNotFoundException::new);

		br.com.pluspet.core.entity.Appointment saveAppointment = mapper.map(appointment,
				br.com.pluspet.core.entity.Appointment.class);

		saveAppointment.setPet(petEntity);

		Appointment savedDTO = mapper.map(service.createAppointment(saveAppointment), Appointment.class);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedDTO.getId())
				.toUri();

		return ResponseEntity.created(location).body(savedDTO);

	}

}
