package com.iliadigital.controledeponto.domain.exception;

public class MomentoNaoEncontradoException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public MomentoNaoEncontradoException(Long momentoId) {
        this(String.format("Momento de código %d não encontrado", momentoId));
    }

    public MomentoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
