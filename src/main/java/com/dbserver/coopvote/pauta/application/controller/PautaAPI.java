package com.dbserver.coopvote.pauta.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dbserver.coopvote.pauta.application.service.PautaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

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