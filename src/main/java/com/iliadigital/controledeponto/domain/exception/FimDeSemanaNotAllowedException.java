package com.iliadigital.controledeponto.domain.exception;

public class FimDeSemanaNotAllowedException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public FimDeSemanaNotAllowedException(String mensagem) {
        super(mensagem);
    }
}
