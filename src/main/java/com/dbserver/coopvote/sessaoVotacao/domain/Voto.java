package com.dbserver.coopvote.sessaoVotacao.domain;

import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sessao_votacao_id")
    private SessaoVotacao sessaoVotacao;
    private String cpfAssociado;
    private OpcaoVoto opcaoVoto;
    private LocalDateTime dataHoraVoto;

    public Voto(SessaoVotacao sessaoVotacao, VotoRequest novoVoto) {
        this.sessaoVotacao = sessaoVotacao;
        this.cpfAssociado = novoVoto.getCpfAssociado();
        this.opcaoVoto = novoVoto.getOpcaoVoto();
        this.dataHoraVoto = LocalDateTime.now();
    }

    public boolean opcaoIgual(OpcaoVoto opcaoVoto) {
        return this.opcaoVoto.equals(opcaoVoto);
    }
}
