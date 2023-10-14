package br.com.pluspet.core.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.pluspet.core.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

	public Optional<Employee> findById(UUID employeeId);

	public UserDetails findByLogin(String login);

}
