package br.com.pluspet.core.enums;

public enum Status {

	AGUARDANDO_ATENDIMENTO(1, "Aguardando Atendimento"), EM_ATENDIMENTO(2, "Em Atendimento"),
	FINALIZADO(3, "Finalizado"), CANCELADO(4, "Cancelado");

	private Integer code;
	private String description;

	private Status(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Status fromCode(Integer code) {
		for (Status status : values()) {
			if (status.code.equals(code)) {
				return status;
			}
		}
		return null;
	}

	public static Status fromDescription(String description) {
		for (Status status : values()) {
			if (status.description.equals(description)) {
				return status;
			}
		}
		return null;
	}

}
