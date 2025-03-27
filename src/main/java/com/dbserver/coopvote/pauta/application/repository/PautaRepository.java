package com.dbserver.coopvote.pauta.application.repository;

import java.util.UUID;

import com.dbserver.coopvote.pauta.domain.Pauta;

public interface PautaRepository {
	void save(Pauta pautaCriada);

	Pauta buscaPautaPorId(UUID idPauta);
}