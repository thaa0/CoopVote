package com.dbserver.coopvote.sessaoVotacao.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
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
	
	public SessaoVotacao(UUID idPauta, Integer tempoDuracao, StatusSessaoVotacao status, LocalDateTime dataHoraAbertura,
			LocalDateTime dataHoraEncerramento) {
		this.idPauta = idPauta;
		this.tempoDuracao = tempoDuracao;
		this.status = status;
		this.dataHoraAbertura = dataHoraAbertura;
		this.dataHoraEncerramento = dataHoraEncerramento;
	}
}