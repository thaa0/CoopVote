package com.dbserver.coopvote.sessaoVotacao.application.controller;

import com.dbserver.coopvote.sessaoVotacao.domain.ResultadoSessao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.dbserver.coopvote.sessaoVotacao.application.service.SessaoVotacaoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

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

	@PostMapping("/{idSessaoVotacao}/voto")
	@ResponseStatus(HttpStatus.CREATED)
	VotoResponse recebeVoto(@PathVariable UUID idSessaoVotacao, @RequestBody VotoRequest novoVoto){
		log.info("[start] SessaoVotacaoAPI - recebeVoto");
		VotoResponse votoResponse = sessaoVotacaoService.registraVoto(idSessaoVotacao, novoVoto);
		log.debug("[finish] SessaoVotacaoAPI - recebeVoto");
		return votoResponse;
	}

	@GetMapping("/{idSessaoVotacao}/resultado")
	@ResponseStatus(HttpStatus.ACCEPTED)
	ResultadoSessaoVotacao obtemResultadoDaSessao(@PathVariable UUID idSessaoVotacao){
		log.info("[start] SessaoVotacaoAPI - buscaResultadoVotacao");
		ResultadoSessaoVotacao resultadoSessao = sessaoVotacaoService.buscaResultadoSessao(idSessaoVotacao);
		log.debug("[finish] SessaoVotacaoAPI - buscaResultadoVotacao");
		return resultadoSessao;
	}

}
