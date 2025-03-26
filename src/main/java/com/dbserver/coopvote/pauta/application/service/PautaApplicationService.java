package com.dbserver.coopvote.pauta.application.service;

import org.springframework.stereotype.Service;

import com.dbserver.coopvote.pauta.application.controller.PautaCriadaResponse;
import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class PautaApplicationService implements PautaService {

	@Override
	public PautaCriadaResponse cadastraPauta(@Valid PautaNovaRequest pauta) {
		// TODO Auto-generated method stub
		return null;
	}

}
