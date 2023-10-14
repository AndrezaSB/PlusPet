package br.com.pluspet.core.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.pluspet.core.entity.Address;
import br.com.pluspet.core.entity.Telephone;
import br.com.pluspet.core.entity.Tutor;
import br.com.pluspet.core.repository.TutorRepository;
import br.com.pluspet.core.vo.TutorFilter;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class TutorServiceTest {

	@Autowired
	private TutorService service;

	@MockBean
	private TutorRepository repository;

	@Test
	void contextLoads() {
		assertNotNull(service);
	}

	private static UUID tutorId = UUID.fromString("af1e5b83-5eac-47d4-80ff-7717916b6a23");
	private static Tutor activeTutor;
	private static Tutor archivedTutor;

	@BeforeAll
	public static void configureMock() {
		activeTutor = new Tutor(tutorId, "Test", LocalDate.of(2023, 10, 11), "11111111111", "test@test.com", false,
				new ArrayList<Address>(), new ArrayList<Telephone>());
		Address address = new Address(UUID.fromString("4ac2251b-d3ce-46db-9087-d2c1577f6c25"), 11111111, "street",
				"number", "district", "city", null, activeTutor);
		Telephone telephone = new Telephone(UUID.fromString("4ac2251b-d3ce-46db-9087-d2c1577f6c52"), "cell",
				"85999999999", activeTutor);

		activeTutor.setAddresses(Arrays.asList(address));
		activeTutor.setTelephones(Arrays.asList(telephone));

		archivedTutor = new Tutor(tutorId, "Test", LocalDate.of(2023, 10, 11), "11111111111", "test@test.com", true,
				new ArrayList<Address>(), new ArrayList<Telephone>());

		archivedTutor.setAddresses(Arrays.asList(address));
		archivedTutor.setTelephones(Arrays.asList(telephone));
	}

	@Test
	void findById() {
		when(repository.findById(tutorId)).thenReturn(Optional.ofNullable(activeTutor));

		Optional<Tutor> tutorResult = service.findById(tutorId);

		assertTrue(tutorResult.isPresent());
		assertEquals(activeTutor, tutorResult.get());

	}

	@Test
	void findByActive(){

		when(repository.findByIdAndArchivedFalse(tutorId)).thenReturn(Optional.ofNullable(activeTutor));

		Optional<Tutor> tutorResult = service.findByIdActive(tutorId);

		assertTrue(tutorResult.isPresent());
		assertEquals(activeTutor, tutorResult.get());

	}

	@Test
	void findActivesTutor() {
		List<Tutor> activeTutors = Arrays.asList(activeTutor);

		Page<Tutor> tutorPage = new PageImpl<Tutor>(activeTutors, Pageable.unpaged(), 1);
		when(repository.findAllByFilter(false, null, null, null, Pageable.unpaged())).thenReturn(tutorPage);

		Page<Tutor> tutorPageResult = service.findActives(TutorFilter.builder().build(), Pageable.unpaged());

		assertNotNull(tutorPageResult);
		assertEquals(activeTutors, tutorPageResult.getContent());

	}

	@Test
	void archiveTutorSuccess() {

		when(repository.findByIdAndArchivedFalse(tutorId)).thenReturn(Optional.ofNullable(activeTutor));
		when(repository.save(archivedTutor)).thenReturn(archivedTutor);

		Tutor tutorResult = service.archiveTutor(tutorId);

		assertNotNull(tutorResult);
		assertEquals(archivedTutor, tutorResult);

	}

	@Test
	void archiveTutorAlreadyArchived() {

		when(repository.findByIdAndArchivedFalse(tutorId)).thenReturn(Optional.ofNullable(null));

		assertThrows(EntityNotFoundException.class, () -> {service.archiveTutor(tutorId);});

	}

	@Test
	void updateTutor() {

		when(repository.findByIdAndArchivedFalse(tutorId)).thenReturn(Optional.ofNullable(activeTutor));
		when(repository.save(archivedTutor)).thenReturn(archivedTutor);

		Tutor tutorResult = service.archiveTutor(tutorId);

		assertNotNull(tutorResult);
		assertEquals(archivedTutor, tutorResult);

	}

	@Test()
	void updateTutorFail() {

		when(repository.findByIdAndArchivedFalse(tutorId)).thenReturn(Optional.ofNullable(null));
		
		assertThrows(EntityNotFoundException.class, () -> {service.updateTutor(archivedTutor);});
	}

}
