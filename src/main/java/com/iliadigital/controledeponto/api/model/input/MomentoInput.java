package com.iliadigital.controledeponto.api.model.input;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MomentoInput {

	@NotNull
	private LocalDateTime dataHora;

	public LocalDateTime getDataHora() {return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
