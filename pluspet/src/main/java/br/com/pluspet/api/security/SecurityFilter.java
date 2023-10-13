package br.com.pluspet.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pluspet.core.repository.EmployeeRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = this.recoverToken(request);

		if (token != null) {
			var login = tokenService.validateToken(token);
			UserDetails employee = employeeRepository.findByLogin(login);

			var authentication = new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);

	}

	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");

		return authHeader == null ? null : authHeader.replace("Bearer ", "");

	}

}
