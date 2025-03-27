package com.dbserver.coopvote.sessaoVotacao.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;

public interface SessaoVotacaoSpringDataJpaRepository extends JpaRepository<SessaoVotacao, UUID>{

}
