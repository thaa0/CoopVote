package com.dbserver.coopvote.sessaoVotacao.application.controller;

import com.dbserver.coopvote.sessaoVotacao.domain.OpcaoVoto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VotoRequest {
    private String cpfAssociado;
    private OpcaoVoto opcaoVoto;
}
