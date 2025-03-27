package test.java.com.dbserver.coopvote.pauta.application.service;

import com.dbserver.coopvote.pauta.application.controller.PautaCriadaResponse;
import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;
import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.application.service.PautaApplicationService;
import com.dbserver.coopvote.pauta.domain.Pauta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PautaApplicationServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaApplicationService pautaApplicationService;

    @Test
    void deveCadastrarPautaQuandoDadosSaoValidos() {
        UUID idAssociadoCriador = UUID.randomUUID();
        PautaNovaRequest request = PautaNovaRequest.builder()
                .titulo("Aprovação do Novo Plano de Benefícios")
                .descricao("Será discutida a aprovação do novo plano de benefícios para os associados.")
                .idAssociadoCriador(idAssociadoCriador)
                .build();
        doNothing().when(pautaRepository).save(any(Pauta.class));
        PautaCriadaResponse response = pautaApplicationService.cadastraPauta(request);
        assertNotNull(response);
        verify(pautaRepository, times(1)).save(any(Pauta.class));
    }

}
