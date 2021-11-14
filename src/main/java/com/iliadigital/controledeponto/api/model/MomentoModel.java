package com.iliadigital.controledeponto.api.model;

import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

public class MomentoModel {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "2021-11-16T08:00:00")
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
