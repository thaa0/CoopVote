package com.dbserver.coopvote.pauta.infra;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.domain.Pauta;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@RequiredArgsConstructor
@Log4j2
public class PautaInfraRepository implements PautaRepository {
	private final PautaSpringDataJpaRepository pautaSpringRepository;

	@Override
	public void save(Pauta pautaCriada) {
		log.info("[start] PautaInfraRepository - save");
		pautaSpringRepository.save(pautaCriada);
		log.debug("[finish] PautaInfraRepository - save");
	}

	@Override
	public Pauta buscaPautaPorId(UUID idPauta) {
		log.info("[start] PautaInfraRepository - buscaPautaPorId");
		Pauta pauta = pautaSpringRepository.findById(idPauta).orElseThrow(
				() -> new EntityNotFoundException("Pauta não encontrada com id: " + idPauta));
		log.debug("[finish] PautaInfraRepository - buscaPautaPorId");
		return pauta;
	}

}