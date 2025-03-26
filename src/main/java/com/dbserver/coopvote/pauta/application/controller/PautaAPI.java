package com.dbserver.coopvote.pauta.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
@Log4j2
@RequiredArgsConstructor
public class PautaAPI {

    private final PautaService pautaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaCriadaResponse cadastraPauta(@RequestBody @Valid PautaNovaRequest pauta) {
        log.info("[start] PautaAPI - cadastraPauta");
        PautaCriadaResponse pautaCriada = pautaService.cadastraPauta(pauta);
        log.debug("[finish] PautaAPI - cadastraPauta");
        return pautaCriada;
    }
}
