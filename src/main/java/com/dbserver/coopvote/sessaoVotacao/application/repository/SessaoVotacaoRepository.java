package com.dbserver.coopvote.sessaoVotacao.application.repository;

import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

import java.util.UUID;

public interface SessaoVotacaoRepository {
	void save(SessaoVotacao sessaoCriada);
    SessaoVotacao buscaSessaoPorId(UUID idSessaoVotacao);
}