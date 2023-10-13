package br.com.pluspet.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.pluspet.api.security.SecurityFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private SecurityFilter securityFilter;

	private static final String[] AUTH_WHITELIST = { "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**",
			"/configuration/ui", "/swagger-resources/**", "/configuration/**", "/webjars/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(authorize -> authorize.requestMatchers(AUTH_WHITELIST).permitAll()
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/v1/appointment").hasRole("ATTENDANT")
						.requestMatchers(HttpMethod.POST, "/v1/pet").hasRole("ATTENDANT")
						.requestMatchers(HttpMethod.PUT, "/v1/pet/**").hasRole("ATTENDANT")
						.requestMatchers(HttpMethod.POST, "/v1/tutor").hasRole("ATTENDANT")
						.requestMatchers(HttpMethod.PUT, "/v1/tutor/**").hasRole("ATTENDANT").anyRequest()
						.authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
