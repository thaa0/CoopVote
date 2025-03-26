package com.dbserver.coopvote.pauta.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbserver.coopvote.pauta.domain.Pauta;

public interface PautaSpringDataJpaRepository extends JpaRepository<Pauta, UUID> {

}
