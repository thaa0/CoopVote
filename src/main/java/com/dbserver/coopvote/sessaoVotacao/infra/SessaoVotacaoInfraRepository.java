package com.dbserver.coopvote.sessaoVotacao.infra;

import org.springframework.stereotype.Repository;

import com.dbserver.coopvote.sessaoVotacao.application.repository.SessaoVotacaoRepository;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Repository
public class SessaoVotacaoInfraRepository implements SessaoVotacaoRepository {

	@Override
	public void save(SessaoVotacao sessaoCriada) {
		log.info("[start] SessaoVotacaoInfraRepository - save");
		log.debug("[finish] SessaoVotacaoInfraRepository - save");
	}

}