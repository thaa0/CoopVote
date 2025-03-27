package com.dbserver.coopvote.sessaoVotacao.application.controller;

import java.util.Optional;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessaoAberturaResquest {
	@NotNull
	private UUID idPauta;
	private Integer tempoDuracao;
	
	public Optional<Integer> getTempoDuracao(){return Optional.ofNullable(tempoDuracao);}
}