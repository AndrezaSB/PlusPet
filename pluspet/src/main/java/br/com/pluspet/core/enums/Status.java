package br.com.pluspet.core.enums;

public enum Status {

	WAITING_SERVICE("Aguardando Atendimento"), IN_PROGRESS("Em Atendimento"), FINISHED("Finalizado"),
	CANCEL("Cancelado");

	private String description;

	private Status(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
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
