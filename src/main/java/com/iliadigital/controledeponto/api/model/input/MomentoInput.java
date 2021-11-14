package com.iliadigital.controledeponto.api.model.input;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class MomentoInput {

	@ApiModelProperty(example = "2021-11-16T08:00:00", required = true)
	@NotNull
	private LocalDateTime dataHora;

	public LocalDateTime getDataHora() {return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
}
