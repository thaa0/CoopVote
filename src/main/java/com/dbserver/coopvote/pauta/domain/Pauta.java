package com.dbserver.coopvote.pauta.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public Pauta(String titulo, LocalDateTime dataHoraCriacao, UUID idAssociadoCriador, String descricao) {
        this.titulo = titulo;
        this.dataHoraCriacao = dataHoraCriacao;
        this.idAssociadoCriador = idAssociadoCriador;
        this.descricao = descricao;
    }

}