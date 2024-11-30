package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Avaliacao;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.exceptions.RequisicaoErradaException;
import origami_flow.salgado_trancas_api.repository.AvaliacaoClienteRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvaliacaoClienteServiceTest {

    @Mock
    private AvaliacaoClienteRepository avaliacaoClienteRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private SalaoService salaoService;

    @Mock
    private AtendimentoRealizadoService atendimentoRealizadoService;

    @InjectMocks
    private AvaliacaoClienteService avaliacaoClienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodasAsAvaliacoes() {
        Avaliacao avaliacao1 = new Avaliacao();
        Avaliacao avaliacao2 = new Avaliacao();
        when(avaliacaoClienteRepository.findAll()).thenReturn(Arrays.asList(avaliacao1, avaliacao2));

        List<Avaliacao> resultado = avaliacaoClienteService.listarAvaliacao();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(avaliacaoClienteRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarAvaliacaoPorId_SeExistir() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(1);
        when(avaliacaoClienteRepository.findById(1)).thenReturn(Optional.of(avaliacao));

        Avaliacao resultado = avaliacaoClienteService.avaliacaoPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(avaliacaoClienteRepository, times(1)).findById(1);
    }

    @Test
    void deveLancarExcecao_SeAvaliacaoNaoExistir() {
        when(avaliacaoClienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> avaliacaoClienteService.avaliacaoPorId(1));
        verify(avaliacaoClienteRepository, times(1)).findById(1);
    }

    @Test
    void deveCriarNovaAvaliacaoComSucesso() {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setNota(5.0);
        AtendimentoRealizado atendimento = new AtendimentoRealizado();
        Cliente cliente = new Cliente();
        Salao salao = new Salao();

        when(atendimentoRealizadoService.atendimentoRealizadoPorId(1)).thenReturn(atendimento);
        when(clienteService.clientePorId(1)).thenReturn(cliente);
        when(salaoService.salaoPorId(1)).thenReturn(salao);
        when(avaliacaoClienteRepository.save(avaliacao)).thenReturn(avaliacao);

        Avaliacao resultado = avaliacaoClienteService.criarAvaliacao(avaliacao, 1, 1, 1);

        assertNotNull(resultado);
        assertEquals(5, resultado.getNota());
        verify(avaliacaoClienteRepository, times(1)).save(avaliacao);
    }

    @Test
    void deveLancarExcecao_SeDadosDeCriacaoForemInvalidos() {
        Avaliacao avaliacao = new Avaliacao();

        assertThrows(RequisicaoErradaException.class, () -> avaliacaoClienteService.criarAvaliacao(avaliacao, null, null, null));
        verify(avaliacaoClienteRepository, never()).save(any());
    }

    @Test
    void deveAtualizarAvaliacaoComSucesso() {
        Avaliacao avaliacaoExistente = new Avaliacao();
        avaliacaoExistente.setId(1);
        Avaliacao avaliacaoAtualizada = new Avaliacao();
        avaliacaoAtualizada.setNota(4.0);

        when(avaliacaoClienteRepository.existsById(1)).thenReturn(true);
        when(avaliacaoClienteRepository.findById(1)).thenReturn(Optional.of(avaliacaoExistente));
        when(avaliacaoClienteRepository.save(avaliacaoExistente)).thenReturn(avaliacaoExistente);

        Avaliacao resultado = avaliacaoClienteService.atualizarAvaliacao(1, avaliacaoAtualizada, null, null, null);

        assertNotNull(resultado);
        assertEquals(4, resultado.getNota());
        verify(avaliacaoClienteRepository, times(1)).save(avaliacaoExistente);
    }

    @Test
    void deveLancarExcecao_SeAvaliacaoNaoExistirAoAtualizar() {
        when(avaliacaoClienteRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> avaliacaoClienteService.atualizarAvaliacao(1, new Avaliacao(), null, null, null));
        verify(avaliacaoClienteRepository, never()).save(any());
    }

    @Test
    void deveDeletarAvaliacaoComSucesso() {
        when(avaliacaoClienteRepository.existsById(1)).thenReturn(true);

        avaliacaoClienteService.deletarAvaliacao(1);

        verify(avaliacaoClienteRepository, times(1)).deleteById(1);
    }

    @Test
    void deveLancarExcecao_SeAvaliacaoNaoExistirAoDeletar() {
        when(avaliacaoClienteRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> avaliacaoClienteService.deletarAvaliacao(1));
        verify(avaliacaoClienteRepository, never()).deleteById(any());
    }
}
