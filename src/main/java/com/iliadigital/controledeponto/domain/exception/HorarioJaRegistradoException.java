package com.iliadigital.controledeponto.domain.exception;

public class HorarioJaRegistradoException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public HorarioJaRegistradoException(String mensagem) {
        super(mensagem);
    }
}
