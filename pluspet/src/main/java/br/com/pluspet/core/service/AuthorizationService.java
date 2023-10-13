package br.com.pluspet.core.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pluspet.core.entity.Employee;
import br.com.pluspet.core.repository.EmployeeRepository;

@Service
public class AuthorizationService extends AbstractService<Employee, UUID, EmployeeRepository>
		implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByLogin(username);
	}

}
