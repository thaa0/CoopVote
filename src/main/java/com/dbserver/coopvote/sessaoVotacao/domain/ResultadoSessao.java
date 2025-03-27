package com.dbserver.coopvote.sessaoVotacao.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
public class ResultadoSessao {
    private int totalVotos;
    private long totalSim;
    private long totalNao;

    public ResultadoSessao(int totalVotos, long totalVotosSim, long totalVotosNao) {
        this.totalVotos = totalVotos;
        this.totalSim = totalVotosSim;
        this.totalNao = totalVotosNao;
    }
}
