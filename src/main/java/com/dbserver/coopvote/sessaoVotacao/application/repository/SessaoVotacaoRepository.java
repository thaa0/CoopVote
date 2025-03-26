package com.dbserver.coopvote.sessaoVotacao.application.repository;

import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

public interface SessaoVotacaoRepository {
	void save(SessaoVotacao sessaoCriada);
}