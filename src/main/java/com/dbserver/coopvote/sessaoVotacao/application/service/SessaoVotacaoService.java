package com.dbserver.coopvote.sessaoVotacao.application.service;

import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAbertaResponse;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;

public interface SessaoVotacaoService {
	SessaoAbertaResponse abreSessao(SessaoAberturaResquest novaSessao);
}