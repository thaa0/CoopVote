package com.dbserver.coopvote.pauta.application.service;

import org.springframework.stereotype.Service;

import com.dbserver.coopvote.pauta.application.controller.PautaCriadaResponse;
import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;
import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.domain.Pauta;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PautaApplicationService implements PautaService {

	private final PautaRepository pautaRepository;

	@Override
	public PautaCriadaResponse cadastraPauta(@Valid PautaNovaRequest pauta) {
		log.info("[start] PautaApplicationService - cadastraPauta");
		Pauta pautaCriada = new Pauta(pauta);
		pautaRepository.save(pautaCriada);
		log.debug("[finish] PautaApplicationService - cadastraPauta");
		return new PautaCriadaResponse(pautaCriada);
	}

}
