package br.com.pluspet.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pluspet.api.security.TokenService;
import br.com.pluspet.core.entity.Employee;
import br.com.pluspet.core.vo.Authentication;
import br.com.pluspet.core.vo.LoginResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid Authentication authentication) {
		
		var userNamePassword = new UsernamePasswordAuthenticationToken(authentication.login(),
				authentication.password());
		var auth = this.authenticationManager.authenticate(userNamePassword);

		return ResponseEntity.ok(new LoginResponse(tokenService.generateToken((Employee) auth.getPrincipal())));
	}

}
