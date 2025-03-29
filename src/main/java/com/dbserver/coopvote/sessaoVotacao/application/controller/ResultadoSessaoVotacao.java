package com.dbserver.coopvote.sessaoVotacao.application.controller;

import com.dbserver.coopvote.sessaoVotacao.domain.ResultadoSessao;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;
import com.dbserver.coopvote.sessaoVotacao.domain.StatusSessaoVotacao;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ResultadoSessaoVotacao {
    private UUID id;
    private UUID idPauta;
    @Enumerated(EnumType.STRING)
    private StatusSessaoVotacao status;
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraEncerramento;
    private int totalVotos;
    private long totalSim;
    private long totalNao;

    public ResultadoSessaoVotacao(SessaoVotacao sessao) {
        this.id = sessao.getId();
        this.idPauta = sessao.getIdPauta();
        this.status = sessao.getStatus();
        this.dataHoraAbertura = sessao.getDataHoraAbertura();
        this.dataHoraEncerramento = sessao.getDataHoraEncerramento();
        this.totalVotos = sessao.getTotalVotos();
        this.totalSim = sessao.getTotalSim();
        this.totalNao = sessao.getTotalNao();
    }
}
