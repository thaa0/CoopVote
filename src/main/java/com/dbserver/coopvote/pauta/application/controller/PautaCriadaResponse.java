package com.dbserver.coopvote.pauta.application.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dbserver.coopvote.pauta.domain.Pauta;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PautaCriadaResponse {
	private UUID id;
    private String titulo;
    private LocalDateTime dataHoraCriacao;

	public PautaCriadaResponse(Pauta pautaCriada) {
		this.id = pautaCriada.getId();
		this.titulo = pautaCriada.getTitulo();
		this.dataHoraCriacao = pautaCriada.getDataHoraCriacao();
    }
}