package com.dbserver.coopvote.pauta.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private UUID id;
    private String titulo;
    private String descricao;
    private UUID idAssociadoCriador;
    private LocalDateTime dataHoraCriacao;

	public Pauta(PautaNovaRequest pauta) {
		this.titulo = pauta.getTitulo();
        this.idAssociadoCriador = pauta.getIdAssociadoCriador();
        this.descricao = pauta.getDescricao();
        this.dataHoraCriacao = LocalDateTime.now();
	}

}