package test.java.com.dbserver.coopvote.sessaoVotacao.application.service;

import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;
import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.domain.Pauta;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAbertaResponse;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;
import com.dbserver.coopvote.sessaoVotacao.application.repository.SessaoVotacaoRepository;
import com.dbserver.coopvote.sessaoVotacao.application.service.SessaoVotacaoAplicationService;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SessaoVotacaoAplicationServiceTest {

    @InjectMocks
    private SessaoVotacaoAplicationService sessaoVotacaoAplicationService;
    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void deveUsarDuracaoPadraoDeUmMinutoQuandoDuracaoNaoForInformadaAoAbrirSessao(){
        UUID idAssociadoCriador = UUID.randomUUID();
        PautaNovaRequest request = PautaNovaRequest.builder()
                .titulo("Aprovação do Novo Plano de Benefícios")
                .descricao("Será discutida a aprovação do novo plano de benefícios para os associados.")
                .idAssociadoCriador(idAssociadoCriador)
                .build();
        Pauta pauta = new Pauta(request);
        SessaoAberturaResquest sessaoRequest = SessaoAberturaResquest.builder()
                .idPauta(pauta.getId())
                .build();

        when(pautaRepository.buscaPautaPorId(pauta.getId())).thenReturn(pauta);
        SessaoAbertaResponse sessaoResponse = sessaoVotacaoAplicationService.abreSessao(sessaoRequest);

        assertEquals(1, sessaoResponse.getTempoDuracao());
        verify(sessaoVotacaoRepository, times(1)).save(any(SessaoVotacao.class));
    }

    @Test
    public void naoDeveAbrirSessaoQuandoIdPautaNaoExiste(){
        UUID idPauta = UUID.randomUUID();
        SessaoAberturaResquest sessaoRequest = SessaoAberturaResquest.builder()
                .idPauta(idPauta)
                .tempoDuracao(2)
                .build();

        doThrow(new EntityNotFoundException("Pauta não encontrada com id: " + idPauta)).when(pautaRepository).buscaPautaPorId(idPauta);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            sessaoVotacaoAplicationService.abreSessao(sessaoRequest);
        });

        verify(sessaoVotacaoRepository, times(0)).save(any(SessaoVotacao.class));
        assertEquals("Pauta não encontrada com id: " + idPauta,exception.getMessage());
    }
}