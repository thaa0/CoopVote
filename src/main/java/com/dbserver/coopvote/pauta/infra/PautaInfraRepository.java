package com.dbserver.coopvote.pauta.infra;

import org.springframework.stereotype.Repository;

import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.domain.Pauta;

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

}