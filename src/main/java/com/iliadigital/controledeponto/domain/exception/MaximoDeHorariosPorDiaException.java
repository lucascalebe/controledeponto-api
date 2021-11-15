package com.iliadigital.controledeponto.domain.exception;

public class MaximoDeHorariosPorDiaException extends ViolacaoDeRegraException {

    private static final long serialVersionUID = 1L;

    public MaximoDeHorariosPorDiaException(String mensagem) {
        super(mensagem);
    }
}
