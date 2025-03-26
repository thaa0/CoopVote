package com.dbserver.coopvote.sessaoVotacao.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/sessao")
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoAPI {
	
	private final SessaoVotacaoService sessaoVotacaoService;

	public SessaoAbertaResponse abreSessao(SessaoAberturaResquest novaSessao) {
		log.info("[start] SessaoVotacaoAPI - abreSessao");
		SessaoAbertaResponse sessaoAbertaResponse = sessaoVotacaoService.abreSessao(novaSessao);
		log.debug("[finish] SessaoVotacaoAPI - abreSessao");
		return sessaoAbertaResponse; 
	}

}
