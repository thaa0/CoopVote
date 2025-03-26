package com.dbserver.coopvote.sessaoVotacao.application.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dbserver.coopvote.sessaoVotacao.domain.StatusSessaoVotacao;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class SessaoAbertaResponse {
	private UUID id;
	private UUID idPauta;
	private Integer tempoDuracao;
	@Enumerated(EnumType.STRING)
	private StatusSessaoVotacao status;
	private LocalDateTime dataHoraAbertura;
	private LocalDateTime dataHoraEncerramento;
}
