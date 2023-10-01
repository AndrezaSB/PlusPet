package br.com.pluspet.v1.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {

	private Long code;

	private String message;

	private Set<String> messages;

	private Throwable exception;

}
