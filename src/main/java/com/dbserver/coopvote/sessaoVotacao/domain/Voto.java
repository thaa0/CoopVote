package com.dbserver.coopvote.sessaoVotacao.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
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

    public Voto(SessaoVotacao sessaoVotacao, String cpfAssociado, OpcaoVoto opcaoVoto, LocalDateTime dataHoraVoto) {
        this.sessaoVotacao = sessaoVotacao;
        this.cpfAssociado = cpfAssociado;
        this.opcaoVoto = opcaoVoto;
        this.dataHoraVoto = dataHoraVoto;
    }
}
