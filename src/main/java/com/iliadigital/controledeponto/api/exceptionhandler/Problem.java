package com.iliadigital.controledeponto.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.OffsetDateTime;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
public class Problem {

    @ApiModelProperty(example = "400")
    private Integer status;

    @ApiModelProperty(example = "https://controledeponto.com.br/dados-invalidos")
    private String type;

    @ApiModelProperty(example = "Dados Inválidos")
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.")
    private String userMessage;

    @ApiModelProperty(example = "2021-11-11T18:09:02.70844Z")
    private OffsetDateTime timestamp;

    public Problem() {
    }

    public Problem(OffsetDateTime timestamp,Integer status, String type, String title, String detail) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
