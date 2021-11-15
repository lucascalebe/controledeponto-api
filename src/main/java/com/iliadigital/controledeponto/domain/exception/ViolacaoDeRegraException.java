package com.iliadigital.controledeponto.domain.exception;

public class ViolacaoDeRegraException extends NegocioException {

    private static final long serialVersionUID = 1L;

    public ViolacaoDeRegraException(String mensagem) {
        super(mensagem);
    }
}
