package com.dbserver.coopvote.associado.infra.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "SerproClientFeign", url = "https://gateway.apiserpro.serpro.gov.br/consulta-cpf-df-trial")
public interface SerproClientFeign {
    @GetMapping("/v1/cpf/{cpfAssociado}")
    SerproClientResponse consultaCPF(@PathVariable String cpfAssociado,
                                            @RequestHeader(value="Authorization") String authorization);
}
