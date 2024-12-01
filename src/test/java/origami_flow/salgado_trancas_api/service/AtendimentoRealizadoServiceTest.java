package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;
import origami_flow.salgado_trancas_api.dto.request.ProdutoUtilizadoRequestDTO;
import origami_flow.salgado_trancas_api.entity.*;
import origami_flow.salgado_trancas_api.exceptions.CaixaNaoAbertoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.AtendimentoRealizadoRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AtendimentoRealizadoServiceTest {

    @InjectMocks
    private AtendimentoRealizadoService atendimentoRealizadoService;

    @Mock
    private AtendimentoRealizadoRepository atendimentoRealizadoRepository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ProdutoAtendimentoUtilizadoService produtoAtendimentoUtilizadoService;

    @Mock
    private CaixaService caixaService;

    private Servico servico;

    private Caixa caixa;

    private Evento evento;

    private Produto produto;

    private Auxiliar auxiliar;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        auxiliar = new Auxiliar();

        produto = new Produto();

        servico = new Servico();

        caixa = new Caixa();

        evento = new Evento();
        evento.setServico(servico);
    }

    @Test
    void listarAtendimentosRealizados_DeveRetornarListaDeAtendimentos() {
        AtendimentoRealizado atendimento1 = new AtendimentoRealizado();
        AtendimentoRealizado atendimento2 = new AtendimentoRealizado();

        when(atendimentoRealizadoRepository.findAll()).thenReturn(List.of(atendimento1, atendimento2));

        List<AtendimentoRealizado> resultado = atendimentoRealizadoService.listarAtendimentosRealizados();

        assertEquals(2, resultado.size());
        verify(atendimentoRealizadoRepository, times(1)).findAll();
    }

    @Test
    void cadastrarAtendimentoRealizado_DeveSalvarAtendimentoComProdutos() {
        ProdutoUtilizadoRequestDTO dto = new ProdutoUtilizadoRequestDTO(1, 2, FinalidadeProdutoAtendimentoEnum.VENDIDO);

        AtendimentoRealizado atendimento = new AtendimentoRealizado();

        produto.setId(1);
        produto.setValorVenda(30.0);

        servico.setValorServico(130.0);
        servico.setValorSinal(15.0);

        caixa.setDataAbertura(LocalDate.now());
        caixa.setDataFechamento(LocalDate.now());
        caixa.setReceitaTotal(0.0);

        when(produtoService.listarTodosPorId(List.of(1))).thenReturn(List.of(produto));
        when(caixaService.buscarCaixaPorMes(anyInt(), anyInt())).thenReturn(caixa);
        when(atendimentoRealizadoRepository.save(any(AtendimentoRealizado.class))).thenReturn(atendimento);

        AtendimentoRealizado resultado = atendimentoRealizadoService.cadastrarAtendimentoRealizado(
                atendimento, evento, List.of(dto));

        assertNotNull(resultado);
        verify(produtoService, times(1)).listarTodosPorId(List.of(1));
        verify(atendimentoRealizadoRepository, times(1)).save(any(AtendimentoRealizado.class));
    }

    @Test
    void cadastrarAtendimentoRealizado_DeveLancarExcecaoQuandoProdutoNaoForEncontrado() {
        Evento evento = new Evento();
        ProdutoUtilizadoRequestDTO dto = new ProdutoUtilizadoRequestDTO(1, 2, FinalidadeProdutoAtendimentoEnum.VENDIDO);

        when(produtoService.listarTodosPorId(List.of(1))).thenReturn(List.of());

        assertThrows(EntidadeNaoEncontradaException.class, () ->
                atendimentoRealizadoService.cadastrarAtendimentoRealizado(new AtendimentoRealizado(), evento, List.of(dto))
        );

        verify(produtoService, times(1)).listarTodosPorId(List.of(1));
    }

    @Test
    void cadastrarAtendimentoRealizado_DeveLancarExcecaoSeCaixaNaoEstiverAberto() {

        servico.setValorServico(130.0);
        servico.setValorSinal(15.0);

        when(caixaService.buscarCaixaPorMes(anyInt(), anyInt())).thenThrow(new CaixaNaoAbertoException(""));

        assertThrows(CaixaNaoAbertoException.class, () ->
                atendimentoRealizadoService.cadastrarAtendimentoRealizado(new AtendimentoRealizado(), evento, List.of())
        );

        verify(caixaService, times(1)).buscarCaixaPorMes(anyInt(), anyInt());
    }

    @Test
    void apagarAtendimentoRealizado_DeveApagarComSucesso() {
        when(atendimentoRealizadoRepository.existsById(1)).thenReturn(true);

        atendimentoRealizadoService.apagarAtendimentoRealizado(1);

        verify(atendimentoRealizadoRepository, times(1)).existsById(1);
        verify(atendimentoRealizadoRepository, times(1)).deleteById(1);
    }

    @Test
    void apagarAtendimentoRealizado_DeveLancarExcecaoQuandoNaoExistir() {
        when(atendimentoRealizadoRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () ->
                atendimentoRealizadoService.apagarAtendimentoRealizado(1)
        );

        verify(atendimentoRealizadoRepository, times(1)).existsById(1);
    }

    @Test
    void buscarCaixaDoMes_DeveLancarExcecaoQuandoCaixaNaoExistir() {
        servico.setValorServico(130.0);
        servico.setValorSinal(15.0);

        when(caixaService.buscarCaixaPorMes(anyInt(), anyInt())).thenReturn(null);

        assertThrows(CaixaNaoAbertoException.class, () -> atendimentoRealizadoService.cadastrarAtendimentoRealizado(
                new AtendimentoRealizado(), evento, List.of())
        );

        verify(caixaService, times(1)).buscarCaixaPorMes(anyInt(), anyInt());
    }

    @Test
    void atualizarReceitaDespesaDoCaixa_DeveAtualizarValores() {
        AtendimentoRealizado atendimento = new AtendimentoRealizado();

        servico.setValorServico(0.0);
        servico.setValorSinal(0.0);

        atendimento.setReceita(500.0);
        auxiliar.setValorMaoDeObra(200.0);
        evento.setAuxiliar(auxiliar);
        atendimento.setEvento(evento);

        caixa.setDataAbertura(LocalDate.now());
        caixa.setDataFechamento(LocalDate.now());

        caixa.setReceitaTotal(1000.0);
        caixa.setDespesaTotal(300.0);

        when(caixaService.buscarCaixaPorMes(anyInt(), anyInt())).thenReturn(caixa);
        when(caixaService.atualizarCaixa(anyInt(), any(Caixa.class), eq(null))).thenReturn(caixa);

        atendimentoRealizadoService.cadastrarAtendimentoRealizado(atendimento, evento, List.of());

        assertEquals(1000.0, caixa.getReceitaTotal());
        assertEquals(500.0, caixa.getDespesaTotal());
    }
}
