package br.com.pluspet.v1.controller;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.pluspet.v1.dto.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class PlusPetExceptionHandler {

	@Autowired
	private PlusPetMessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> error400(MethodArgumentNotValidException ex) {
		Set<String> messages = ex.getBindingResult().getAllErrors().stream().map(err -> messageSource.getMessage(err))
				.collect(toSet());
		return ResponseEntity.badRequest()
				.body(ErrorMessage.builder()
						.message("Não foi possível finalizar sua ação. Favor entrar em contato com o Administrador")
						.messages(messages).build());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> error404() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorMessage> manageError400(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(ErrorMessage.builder()
				.message("Não foi possível finalizar sua ação. Favor entrar em contato com o Administrador").build());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> manageErro500(Exception ex) {
		return ResponseEntity.internalServerError().body("Error: " + ex.getLocalizedMessage());
	}
}
