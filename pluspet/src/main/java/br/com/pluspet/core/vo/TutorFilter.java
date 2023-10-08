package br.com.pluspet.core.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TutorFilter {

	private String name;
	private String cpf;
	private String email;
}
