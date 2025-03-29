package com.dbserver.coopvote.sessaoVotacao.application.controller;

import com.dbserver.coopvote.sessaoVotacao.domain.ResultadoSessao;
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
    @Embedded
    private ResultadoSessao resultado;
}
