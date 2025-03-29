package com.dbserver.coopvote.sessaoVotacao.application.controller;

import com.dbserver.coopvote.sessaoVotacao.domain.OpcaoVoto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class VotoRequest {
    private String cpfAssociado;
    private OpcaoVoto opcaoVoto;
}
