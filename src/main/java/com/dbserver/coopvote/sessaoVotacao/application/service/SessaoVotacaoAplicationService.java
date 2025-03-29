package com.dbserver.coopvote.sessaoVotacao.application.service;

import com.dbserver.coopvote.associado.application.service.AssociadoService;
import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoRequest;
import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoResponse;
import com.dbserver.coopvote.sessaoVotacao.domain.Voto;
import org.springframework.stereotype.Service;

import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.domain.Pauta;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAbertaResponse;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;
import com.dbserver.coopvote.sessaoVotacao.application.repository.SessaoVotacaoRepository;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class SessaoVotacaoAplicationService implements SessaoVotacaoService {

	private final SessaoVotacaoRepository sessaoVotacaoRepository;
	private final PautaRepository pautaRepository;
	private final AssociadoService associadoService;

	@Override
	public SessaoAbertaResponse abreSessao(SessaoAberturaResquest novaSessao) {
		log.info("[start] SessaoVotacaoApllicationService - abreSessao");
		Pauta pauta = pautaRepository.buscaPautaPorId(novaSessao.getIdPauta());
		SessaoVotacao sessaoCriada =  new SessaoVotacao(novaSessao, pauta);
		sessaoVotacaoRepository.save(sessaoCriada);
		log.debug("[finish] SessaoVotacaoApllicationService - abreSessao");
		return new SessaoAbertaResponse(sessaoCriada);
	}

	@Override
	public VotoResponse registraVoto(UUID idSessaoVotacao, VotoRequest novoVoto) {
		log.info("[start] SessaoVotacaoAplicationService - registraVoto");
		SessaoVotacao sessao = sessaoVotacaoRepository.buscaSessaoPorId(idSessaoVotacao);
		Voto voto = sessao.recebeVoto(novoVoto, associadoService);
		sessaoVotacaoRepository.save(sessao);
		log.debug("[finish] SessaoVotacaoAplicationService - registraVoto");
		return new VotoResponse(voto);
	}

}
