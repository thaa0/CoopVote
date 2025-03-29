package com.dbserver.coopvote.sessaoVotacao.application.service;

import com.dbserver.coopvote.sessaoVotacao.application.controller.*;

import java.util.UUID;

public interface SessaoVotacaoService {
	SessaoAbertaResponse abreSessao(SessaoAberturaResquest novaSessao);
    VotoResponse registraVoto(UUID idSessaoVotacao, VotoRequest novoVoto);
    ResultadoSessaoVotacao buscaResultadoSessao(UUID idSessaoVotacao);
}