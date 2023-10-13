package br.com.pluspet.api.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.pluspet.core.entity.Employee;

@Service
public class TokenService {

	private static String issuer = "pluspet-auth-api";

	@Value("{pluspet.secutiry.secret}")
	private String secret;

	public String generateToken(Employee employee) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create().withIssuer(issuer).withSubject(secret).withSubject(employee.getLogin())
					.withExpiresAt(generateExpirationDate()).sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Error while generating token", exception);
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm).withIssuer(issuer).build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			return "";
		}
	}

	private Instant generateExpirationDate() {
		return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
	}

}
