package com.dbserver.coopvote.associado.application.service;

import com.dbserver.coopvote.associado.infra.client.SerproClientFeign;
import com.dbserver.coopvote.associado.infra.client.SerproClientResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AssociadoApplicationService implements AssociadoService {
    private final SerproClientFeign serproClientFeign;

    @Override
    public void validaAptidaoAoVoto(String cpfAssociado) {
        log.info("[start] AssociadoApplicationService - validaAptidaoAoVoto");
        SerproClientResponse response = serproClientFeign.consultaCPF(cpfAssociado, TOKEN);
        valida(response);
        log.debug("[finish] AssociadoApplicationService - validaAptidaoAoVoto");
    }

    private void valida(SerproClientResponse response) {
        if(response.isInvalid()){
            throw new RuntimeException("CPF Não é válido para votação!");
        }
    }

    private static final String TOKEN = "Bearer 06aef429-a981-3ec5-a1f8-71d38d86481e";
}