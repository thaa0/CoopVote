package com.dbserver.coopvote.sessaoVotacao.application.service;

import org.springframework.stereotype.Service;

import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAbertaResponse;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;
import com.dbserver.coopvote.sessaoVotacao.application.repository.SessaoVotacaoRepository;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoApllicationService implements SessaoVotacaoService {

	private final SessaoVotacaoRepository sessaoVotacaoRepository;

	@Override
	public SessaoAbertaResponse abreSessao(SessaoAberturaResquest novaSessao) {
		log.info("[start] SessaoVotacaoApllicationService - abreSessao");
		SessaoVotacao sessaoCriada =  new SessaoVotacao(novaSessao);
		sessaoVotacaoRepository.save(sessaoCriada);
		log.debug("[finish] SessaoVotacaoApllicationService - abreSessao");
		return new SessaoAbertaResponse(sessaoCriada);
	}

}
