package com.dbserver.coopvote.sessaoVotacao.application.service;

import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAbertaResponse;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;
import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoRequest;
import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoResponse;

import java.util.UUID;

public interface SessaoVotacaoService {
	SessaoAbertaResponse abreSessao(SessaoAberturaResquest novaSessao);
    VotoResponse registraVoto(UUID idSessaoVotacao, VotoRequest novoVoto);
}