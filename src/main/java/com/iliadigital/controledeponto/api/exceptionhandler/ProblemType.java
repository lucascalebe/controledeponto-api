package com.iliadigital.controledeponto.api.exceptionhandler;

public enum ProblemType {

    DADOS_INVALIDOS("/dados-invalidos","Dados inválidos"),
    METODO_NAO_SUPORTADO("/metodo-nao-suportado", "Método HTTP não suportado"),
    ERRO_DE_SISTEMA("/erro-de-sistema","Erro de Sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido","Parâmetro Inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreesivel"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado","Recurso não encontrado"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");


    private String title;
    private String uri;

    private ProblemType(String path, String title) {
        this.uri = "https://controledeponto.com.br" + path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
