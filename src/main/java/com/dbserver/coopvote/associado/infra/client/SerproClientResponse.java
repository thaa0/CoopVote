package com.dbserver.coopvote.associado.infra.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SerproClientResponse {
    @JsonProperty("ni")
    private String ni;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("situacao")
    private Situacao situacao;
    @JsonProperty("nascimento")
    private String nascimento;

    @Getter
    private static class Situacao {
        @JsonProperty("codigo")
        private String codigo;
        @JsonProperty("descricao")
        private String descricao;
    }
}
