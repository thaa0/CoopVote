package com.dbserver.coopvote.pauta.application.controller;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class PautaNovaRequest {
    private String titulo;
    private String descricao;
    private UUID idAssociadoCriador;
    private LocalDateTime dataHoraCriacao;
}
