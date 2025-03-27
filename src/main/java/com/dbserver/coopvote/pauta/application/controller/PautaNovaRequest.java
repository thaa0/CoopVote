package com.dbserver.coopvote.pauta.application.controller;

import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PautaNovaRequest {
    private String titulo;
    private String descricao;
    private UUID idAssociadoCriador;
}
