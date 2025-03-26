package com.dbserver.coopvote.pauta.application.service;

import com.dbserver.coopvote.pauta.application.controller.PautaCriadaResponse;
import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;

import jakarta.validation.Valid;

public interface PautaService {
    PautaCriadaResponse cadastraPauta(@Valid PautaNovaRequest pauta);
}
