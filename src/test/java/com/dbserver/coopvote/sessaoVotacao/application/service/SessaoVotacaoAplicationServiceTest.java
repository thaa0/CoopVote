package com.dbserver.coopvote.sessaoVotacao.application.service;

import com.dbserver.coopvote.associado.application.service.AssociadoService;
import com.dbserver.coopvote.pauta.application.controller.PautaNovaRequest;
import com.dbserver.coopvote.pauta.application.repository.PautaRepository;
import com.dbserver.coopvote.pauta.domain.Pauta;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAbertaResponse;
import com.dbserver.coopvote.sessaoVotacao.application.controller.SessaoAberturaResquest;
import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoRequest;
import com.dbserver.coopvote.sessaoVotacao.application.controller.VotoResponse;
import com.dbserver.coopvote.sessaoVotacao.application.repository.SessaoVotacaoRepository;
import com.dbserver.coopvote.sessaoVotacao.application.service.SessaoVotacaoAplicationService;
import com.dbserver.coopvote.sessaoVotacao.domain.OpcaoVoto;
import com.dbserver.coopvote.sessaoVotacao.domain.SessaoVotacao;
import com.dbserver.coopvote.sessaoVotacao.domain.Voto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    @Mock
    private AssociadoService associadoService;
    @Mock
    private SessaoVotacao sessaoVotacao;

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
    public void deveAbrirSessaoQuandoDadosValidos(){
        UUID idAssociadoCriador = UUID.randomUUID();
        PautaNovaRequest request = PautaNovaRequest.builder()
                .titulo("Aprovação do Novo Plano de Benefícios")
                .descricao("Será discutida a aprovação do novo plano de benefícios para os associados.")
                .idAssociadoCriador(idAssociadoCriador)
                .build();
        Pauta pauta = new Pauta(request);
        SessaoAberturaResquest sessaoRequest = SessaoAberturaResquest.builder()
                .idPauta(pauta.getId())
                .tempoDuracao(2)
                .build();

        when(pautaRepository.buscaPautaPorId(pauta.getId())).thenReturn(pauta);
        SessaoAbertaResponse sessaoResponse = sessaoVotacaoAplicationService.abreSessao(sessaoRequest);

        assertEquals(2, sessaoResponse.getTempoDuracao());
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

    @Test
    public void deveRegistrarVotoQuandoCPFAssociadoEValido(){
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
        SessaoVotacao sessao = new SessaoVotacao(sessaoRequest, pauta);
        String cpfValido = "63017285995";
        VotoRequest votoRequest = VotoRequest.builder()
                .cpfAssociado(cpfValido)
                .opcaoVoto(OpcaoVoto.SIM)
                .build();
        Voto voto = new Voto(sessao, votoRequest);
        VotoResponse responseExpected = new VotoResponse(voto);

        when(sessaoVotacaoRepository.buscaSessaoPorId(sessao.getId())).thenReturn(sessao);
        doNothing().when(sessaoVotacaoRepository).save(sessao);

        VotoResponse responseTest = sessaoVotacaoAplicationService.registraVoto(sessao.getId(), votoRequest);

        verify(sessaoVotacaoRepository, times(1)).save(sessao);
        verify(sessaoVotacaoRepository, times(1)).buscaSessaoPorId(sessao.getId());
        assertThat(responseExpected.getCpfAssociado()).isEqualTo(responseTest.getCpfAssociado());
        assertThat(responseExpected.getOpcaoVoto()).isEqualTo(responseTest.getOpcaoVoto());
    }

    @Test
    public void naoDeveRegistrarVotoQuandoCPFAssociadoEInvalido(){
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
        SessaoVotacao sessao = new SessaoVotacao(sessaoRequest, pauta);
        String cpfInvalido = "40532176871";
        VotoRequest votoRequest = VotoRequest.builder()
                .cpfAssociado(cpfInvalido)
                .opcaoVoto(OpcaoVoto.SIM)
                .build();
        Voto voto = new Voto(sessao, votoRequest);

        when(sessaoVotacaoRepository.buscaSessaoPorId(sessao.getId())).thenReturn(sessao);

        doThrow(new RuntimeException("CPF Não é válido para votação!"))
                .when(associadoService).validaAptidaoAoVoto(voto.getCpfAssociado());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sessaoVotacaoAplicationService.registraVoto(sessao.getId(), votoRequest);
        });

        verify(sessaoVotacaoRepository, times(0)).save(sessao);
        verify(sessaoVotacaoRepository, times(1)).buscaSessaoPorId(sessao.getId());
        assertThat(exception.getMessage()).isEqualTo("CPF Não é válido para votação!");
    }

    @Test
    public void naoDeveRegistrarVotoQuandoSessaoFechada(){
        UUID idAssociadoCriador = UUID.randomUUID();
        PautaNovaRequest request = PautaNovaRequest.builder()
                .titulo("Aprovação do Novo Plano de Benefícios")
                .descricao("Será discutida a aprovação do novo plano de benefícios para os associados.")
                .idAssociadoCriador(idAssociadoCriador)
                .build();
        Pauta pauta = new Pauta(request);

        SessaoAberturaResquest sessaoRequest = SessaoAberturaResquest.builder()
                .idPauta(pauta.getId())
                .tempoDuracao(0)
                .build();
        SessaoVotacao sessao = new SessaoVotacao(sessaoRequest, pauta);
        String cpfValido = "63017285995";
        VotoRequest votoRequest = VotoRequest.builder()
                .cpfAssociado(cpfValido)
                .opcaoVoto(OpcaoVoto.SIM)
                .build();

        when(sessaoVotacaoRepository.buscaSessaoPorId(sessao.getId())).thenReturn(sessao);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sessaoVotacaoAplicationService.registraVoto(sessao.getId(), votoRequest);
        });

        verify(sessaoVotacaoRepository, times(0)).save(sessao);
        verify(sessaoVotacaoRepository, times(1)).buscaSessaoPorId(sessao.getId());
        assertThat(exception.getMessage()).isEqualTo("Esta sessão não aceita mais votos!");
    }
}