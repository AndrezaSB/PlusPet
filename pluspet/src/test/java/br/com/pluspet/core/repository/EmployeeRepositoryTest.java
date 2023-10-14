package br.com.pluspet.core.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.pluspet.core.entity.Employee;
import br.com.pluspet.core.enums.Role;

@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Test
	void findByEmployeeIdSucess() {
		UUID employeeId = UUID.fromString("60686451-0f0f-4565-81ed-b8a13b7436b1");

		Employee expectedEmployee = new Employee();
		expectedEmployee.setId(employeeId);
		expectedEmployee.setName("Viviane Amarante");
		expectedEmployee.setLogin("drvivi");
		expectedEmployee.setPassword("$2a$12$6Grk9DCwVGLLxl./rLltm.3bwQZ8lzDzXQZ1/S9GO/BoQQPmJr6xy");
		expectedEmployee.setCRMV("CE-28891");
		expectedEmployee.setRole(Role.VETERINARIAN);

		Optional<Employee> employee = repository.findById(employeeId);

		assertTrue(employee.isPresent());

		assertEquals(expectedEmployee, employee.get());
	}

	@Test
	void findByEmployeeIdFail() {
		UUID employeeId = UUID.fromString("60686451-0f0f-4565-81ed-b8a13b7436b9");

		Optional<Employee> employee = repository.findById(employeeId);

		assertFalse(employee.isPresent());
	}

	@Test
	void findByLoginSucess() {
		String loginUserName = "tiana_f";

		Employee expectedEmployee = new Employee();
		expectedEmployee.setId(UUID.fromString("60686451-0f0f-4565-81ed-b8a13b7436b4"));
		expectedEmployee.setName("Tiana Frog");
		expectedEmployee.setLogin("tiana_f");
		expectedEmployee.setPassword("$2a$12$6Grk9DCwVGLLxl./rLltm.3bwQZ8lzDzXQZ1/S9GO/BoQQPmJr6xy");
		expectedEmployee.setRole(Role.ADMIN);

		UserDetails employee = repository.findByLogin(loginUserName);

		assertNotNull(employee);
	}

	@Test
	void findByLoginFail() {
		String loginUserName = "fail_login";

		UserDetails employee = repository.findByLogin(loginUserName);

		assertNull(employee);
	}

}
