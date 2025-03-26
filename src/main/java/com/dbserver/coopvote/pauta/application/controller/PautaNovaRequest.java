package com.dbserver.coopvote.pauta.application.controller;

import java.util.UUID;

import lombok.Getter;

@Getter
public class PautaNovaRequest {
    private String titulo;
    private String descricao;
    private UUID idAssociadoCriador;
}
