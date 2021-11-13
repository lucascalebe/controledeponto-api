package com.iliadigital.controledeponto.api.model.input;

import java.time.LocalDateTime;

public class MomentoInput {

	private Long id;
	
	private LocalDateTime dataHora;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
