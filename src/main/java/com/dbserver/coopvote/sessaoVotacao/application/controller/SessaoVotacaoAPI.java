package com.dbserver.coopvote.sessaoVotacao.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbserver.coopvote.sessaoVotacao.application.service.SessaoVotacaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/sessao")
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoAPI {
	
	private final SessaoVotacaoService sessaoVotacaoService;

	@PostMapping("/abertura")
	@ResponseStatus(HttpStatus.CREATED)
	public SessaoAbertaResponse abreSessao(@RequestBody SessaoAberturaResquest novaSessao) {
		log.info("[start] SessaoVotacaoAPI - abreSessao");
		SessaoAbertaResponse sessaoAbertaResponse = sessaoVotacaoService.abreSessao(novaSessao);
		log.debug("[finish] SessaoVotacaoAPI - abreSessao");
		return sessaoAbertaResponse; 
	}

}
