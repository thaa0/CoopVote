package com.dbserver.coopvote.sessaoVotacao.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.dbserver.coopvote.pauta.domain.Pauta;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SessaoVotacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "uuid", updatable = false, unique = true, nullable = false)
	private UUID id;
	private UUID idPauta;
	private Integer tempoDuracao;
	@Enumerated(EnumType.STRING)
	private StatusSessaoVotacao status;
	private LocalDateTime dataHoraAbertura;
	private LocalDateTime dataHoraEncerramento;


	public SessaoVotacao(SessaoAberturaResquest novaSessao, Pauta pauta) {
		this.idPauta = pauta.getId();
		this.tempoDuracao = novaSessao.getTempoDuracao().orElse(1);
		this.status = StatusSessaoVotacao.ABERTO;
		this.dataHoraAbertura = LocalDateTime.now();
		this.dataHoraEncerramento = LocalDateTime.now().plusMinutes(this.tempoDuracao);
	}
}