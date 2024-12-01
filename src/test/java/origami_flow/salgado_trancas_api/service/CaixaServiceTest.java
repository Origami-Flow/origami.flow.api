package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.CaixaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CaixaServiceTest {

    @InjectMocks
    private CaixaService caixaService;

    @Mock
    private CaixaRepository caixaRepository;

    @Mock
    private SalaoService salaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodosCaixas_DeveRetornarListaDeCaixas() {
        List<Caixa> caixas = List.of(new Caixa(), new Caixa());
        when(caixaRepository.findAll()).thenReturn(caixas);

        List<Caixa> resultado = caixaService.listarTodosCaixas();

        assertEquals(2, resultado.size());
        verify(caixaRepository, times(1)).findAll();
    }

    @Test
    void caixaPorId_DeveRetornarCaixaQuandoEncontrado() {
        Caixa caixa = new Caixa();
        caixa.setId(1);
        when(caixaRepository.findById(1)).thenReturn(Optional.of(caixa));

        Caixa resultado = caixaService.caixaPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(caixaRepository, times(1)).findById(1);
    }

    @Test
    void caixaPorId_DeveLancarExcecaoQuandoNaoEncontrado() {
        when(caixaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> caixaService.caixaPorId(1));
        verify(caixaRepository, times(1)).findById(1);
    }

    @Test
    void abrirCaixa_DeveSalvarENovoCaixa() {
        Salao salao = new Salao();
        salao.setId(1);
        Caixa caixa = new Caixa();
        LocalDate inicio = LocalDate.of(2023, 1, 1);
        LocalDate termino = LocalDate.of(2023, 1, 31);

        when(salaoService.salaoPorId(1)).thenReturn(salao);
        when(caixaRepository.save(any(Caixa.class))).thenReturn(caixa);

        Caixa resultado = caixaService.abrirCaixa(1, inicio, termino);

        assertNotNull(resultado);
        verify(salaoService, times(1)).salaoPorId(1);
        verify(caixaRepository, times(1)).save(any(Caixa.class));
    }

    @Test
    void atualizarCaixa_DeveAtualizarECaixa() {
        Caixa caixa = new Caixa();
        caixa.setId(1);
        caixa.setDataAbertura(LocalDate.of(2023, 1, 1));
        caixa.setDataFechamento(LocalDate.of(2023, 1, 31));

        when(caixaRepository.findById(1)).thenReturn(Optional.of(caixa));
        when(caixaRepository.save(any(Caixa.class))).thenReturn(caixa);

        Caixa atualizado = new Caixa();
        atualizado.setDataAbertura(LocalDate.of(2023, 2, 1));

        Caixa resultado = caixaService.atualizarCaixa(1, atualizado, null);

        assertNotNull(resultado);
        assertEquals(LocalDate.of(2023, 2, 1), resultado.getDataAbertura());
        verify(caixaRepository, times(1)).save(any(Caixa.class));
    }

    @Test
    void deletarCaixa_DeveExcluirCaixaQuandoExistir() {
        when(caixaRepository.existsById(1)).thenReturn(true);

        caixaService.deletarCaixa(1);

        verify(caixaRepository, times(1)).deleteById(1);
    }

    @Test
    void deletarCaixa_DeveLancarExcecaoQuandoNaoExistir() {
        when(caixaRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> caixaService.deletarCaixa(1));
        verify(caixaRepository, never()).deleteById(1);
    }

    @Test
    void buscarCaixaPorMes_DeveRetornarCaixaQuandoEncontrado() {
        Caixa caixa = new Caixa();
        when(caixaRepository.buscarCaixaPorMes(1, 2023)).thenReturn(Optional.of(caixa));

        Caixa resultado = caixaService.buscarCaixaPorMes(1, 2023);

        assertNotNull(resultado);
        verify(caixaRepository, times(1)).buscarCaixaPorMes(1, 2023);
    }

    @Test
    void buscarCaixaPorMes_DeveRetornarNullQuandoNaoEncontrado() {
        when(caixaRepository.buscarCaixaPorMes(1, 2023)).thenReturn(Optional.empty());

        Caixa resultado = caixaService.buscarCaixaPorMes(1, 2023);

        assertNull(resultado);
        verify(caixaRepository, times(1)).buscarCaixaPorMes(1, 2023);
    }

    @Test
    void listarTodosCaixas_DeveRetornarListaVaziaQuandoNaoExistiremCaixas() {
        when(caixaRepository.findAll()).thenReturn(List.of());

        List<Caixa> resultado = caixaService.listarTodosCaixas();

        assertTrue(resultado.isEmpty());
        verify(caixaRepository, times(1)).findAll();
    }

    @Test
    void abrirCaixa_DeveLancarExcecaoQuandoSalaoNaoExistir() {
        when(salaoService.salaoPorId(99)).thenThrow(new EntidadeNaoEncontradaException("salao"));

        assertThrows(EntidadeNaoEncontradaException.class, () ->
                caixaService.abrirCaixa(99, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 31))
        );

        verify(salaoService, times(1)).salaoPorId(99);
        verify(caixaRepository, never()).save(any(Caixa.class));
    }

    @Test
    void atualizarCaixa_DeveManterValoresOriginaisQuandoCamposNaoForemFornecidos() {
        Caixa caixa = new Caixa();
        caixa.setId(1);
        caixa.setReceitaTotal(1000.0);
        caixa.setDespesaTotal(500.0);

        when(caixaRepository.findById(1)).thenReturn(Optional.of(caixa));
        when(caixaRepository.save(any(Caixa.class))).thenReturn(caixa);

        Caixa atualizado = new Caixa();
        Caixa resultado = caixaService.atualizarCaixa(1, atualizado, null);

        assertEquals(1000.0, resultado.getReceitaTotal());
        assertEquals(500.0, resultado.getDespesaTotal());
        verify(caixaRepository, times(1)).save(caixa);
    }

    @Test
    void deletarCaixa_DeveLancarExcecaoSeIdForNulo() {
        assertThrows(IllegalArgumentException.class, () -> caixaService.deletarCaixa(null));
        verify(caixaRepository, never()).existsById(any());
        verify(caixaRepository, never()).deleteById(any());
    }

    @Test
    void buscarCaixaPorMes_DeveLancarExcecaoParaMesOuAnoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> caixaService.buscarCaixaPorMes(13, 2023));
        assertThrows(IllegalArgumentException.class, () -> caixaService.buscarCaixaPorMes(-1, 2023));
        assertThrows(IllegalArgumentException.class, () -> caixaService.buscarCaixaPorMes(1, -2023));
        verify(caixaRepository, never()).buscarCaixaPorMes(anyInt(), anyInt());
    }

    @Test
    void abrirCaixa_DeveDefinirValoresPadraoParaReceitaEDespesa() {
        Salao salao = new Salao();
        salao.setId(1);

        when(salaoService.salaoPorId(1)).thenReturn(salao);
        when(caixaRepository.save(any(Caixa.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Caixa resultado = caixaService.abrirCaixa(1, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 31));

        assertEquals(0.0, resultado.getReceitaTotal());
        assertEquals(0.0, resultado.getDespesaTotal());
        verify(caixaRepository, times(1)).save(any(Caixa.class));
    }
}