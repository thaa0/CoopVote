package com.dbserver.coopvote.sessaoVotacao.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import com.dbserver.coopvote.sessaoVotacao.application.repository.SessaoVotacaoRepository;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.UUID;

@RequiredArgsConstructor
@Log4j2
@Repository
public class SessaoVotacaoInfraRepository implements SessaoVotacaoRepository {

	private final SessaoVotacaoSpringDataJpaRepository sessaoVotacaoSpringDataJpaRepository;

	@Override
	public void save(SessaoVotacao sessaoCriada) {
		log.info("[start] SessaoVotacaoInfraRepository - save");
		sessaoVotacaoSpringDataJpaRepository.save(sessaoCriada);
		log.debug("[finish] SessaoVotacaoInfraRepository - save");
	}

	@Override
	public SessaoVotacao buscaSessaoPorId(UUID idSessaoVotacao) {
		log.info("[start] SessaoVotacaoInfraRepository - buscaSessaoPorId");
		SessaoVotacao sessao = sessaoVotacaoSpringDataJpaRepository.findById(idSessaoVotacao)
				.orElseThrow(() -> new EntityNotFoundException("Sessao Nao Encontrada"));
		log.debug("[finish] SessaoVotacaoInfraRepository - buscaSessaoPorId");
		return sessao;
	}

}