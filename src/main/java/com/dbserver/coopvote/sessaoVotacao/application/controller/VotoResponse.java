package com.dbserver.coopvote.sessaoVotacao.application.controller;

import com.dbserver.coopvote.sessaoVotacao.domain.OpcaoVoto;
import com.dbserver.coopvote.sessaoVotacao.domain.Voto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VotoResponse {
    private final String cpfAssociado;
    private final OpcaoVoto opcaoVoto;
    private final LocalDateTime dataHoraVoto;

    public VotoResponse(Voto voto) {
        this.cpfAssociado = voto.getCpfAssociado();
        this.opcaoVoto = voto.getOpcaoVoto();
        this.dataHoraVoto = LocalDateTime.now();
    }
}
