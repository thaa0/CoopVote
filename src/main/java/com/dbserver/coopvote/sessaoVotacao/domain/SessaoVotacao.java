package com.dbserver.coopvote.sessaoVotacao.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.dbserver.coopvote.associado.application.service.AssociadoService;
import com.dbserver.coopvote.pauta.domain.Pauta;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;

import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoRequest;
import jakarta.persistence.*;
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
	private int totalVotos;
	private long totalSim;
	private long totalNao;
	@Enumerated(EnumType.STRING)
	private StatusSessaoVotacao status;
	private LocalDateTime dataHoraAbertura;
	private LocalDateTime dataHoraEncerramento;

	@OneToMany(mappedBy = "sessaoVotacao", cascade = CascadeType.ALL, orphanRemoval = true)
	@MapKey(name = "cpfAssociado")
	private Map<String, Voto> votos;

	public SessaoVotacao(SessaoAberturaResquest novaSessao, Pauta pauta) {
		this.idPauta = pauta.getId();
		this.tempoDuracao = novaSessao.getTempoDuracao().orElse(1);
		this.status = StatusSessaoVotacao.ABERTO;
		this.dataHoraAbertura = LocalDateTime.now();
		this.dataHoraEncerramento = LocalDateTime.now().plusMinutes(this.tempoDuracao);
		this.votos = new HashMap<>();
		this.totalVotos = 0;
		this.totalSim = 0;
		this.totalNao = 0;

	}

    public Voto recebeVoto(VotoRequest novoVoto, AssociadoService associadoService) {
		validaSessaoAberta();
		validaAssociado(novoVoto.getCpfAssociado(), associadoService);
		Voto voto = new Voto(this, novoVoto);
		votos.put(novoVoto.getCpfAssociado(), voto);
		return voto;
    }

	private void validaAssociado(String cpfAssociado,  AssociadoService associadoService) {
		validaVotoDuplicado(cpfAssociado);
		validaAptidaoVoto(associadoService, cpfAssociado);
	}

	private void validaAptidaoVoto(AssociadoService associadoService, String cpfAssociado) {
		associadoService.validaAptidaoAoVoto(cpfAssociado);
	}

	private void validaVotoDuplicado(String cpfAssociado) {
		if (votos.containsKey(cpfAssociado)){
			throw new RuntimeException("Associado ja votou nessa sessao!");
		}
	}

	public void validaSessaoAberta() {
		atualizaStatus();
		if (status.equals(StatusSessaoVotacao.FECHADO)){
			throw new RuntimeException("Esta sessão não aceita mais votos!");
		}
	}

	public void atualizaStatus() {
		if(LocalDateTime.now().isAfter(dataHoraEncerramento)){
			this.status = StatusSessaoVotacao.FECHADO;
			obtemResultado();
		}
	}

	public void obtemResultado() {
		this.totalVotos = totalDeVotos();
		this.totalSim = totalVotoSim();
		this.totalNao = totalVotoNao();
	}

	private long totalVotoSim() {
		return calculaOpcaoVotos(OpcaoVoto.SIM);
	}

	private long totalVotoNao() {
		return calculaOpcaoVotos(OpcaoVoto.NAO);
	}

	private long calculaOpcaoVotos(OpcaoVoto opcaoVoto) {
		return votos.values().stream()
				.filter(voto -> voto.opcaoIgual(opcaoVoto))
				.count();
	}

	private int totalDeVotos() {
		return this.votos.size();
	}
}