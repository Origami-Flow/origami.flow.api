package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;
import origami_flow.salgado_trancas_api.repository.ProdutoAtendimentoUtilizadoRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoAtendimentoUtilizadoServiceTest {

    @InjectMocks
    private ProdutoAtendimentoUtilizadoService produtoAtendimentoUtilizadoService;

    @Mock
    private ProdutoAtendimentoUtilizadoRepository produtoAtendimentoUtilizadoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrarProdutoUtilizado_DeveRegistrarComSucesso() {
        AtendimentoRealizado atendimento = new AtendimentoRealizado();
        ProdutoAtendimentoUtilizado produto1 = new ProdutoAtendimentoUtilizado();
        ProdutoAtendimentoUtilizado produto2 = new ProdutoAtendimentoUtilizado();

        List<ProdutoAtendimentoUtilizado> produtos = List.of(produto1, produto2);

        when(produtoAtendimentoUtilizadoRepository.saveAll(produtos)).thenReturn(produtos);

        List<ProdutoAtendimentoUtilizado> resultado = produtoAtendimentoUtilizadoService.registrarProdutoUtilizado(produtos, atendimento);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(atendimento, produto1.getAtendimentoRealizado());
        assertEquals(atendimento, produto2.getAtendimentoRealizado());

        verify(produtoAtendimentoUtilizadoRepository, times(1)).saveAll(produtos);
    }

    @Test
    void registrarProdutoUtilizado_DeveManterReferenciaDeAtendimento() {
        AtendimentoRealizado atendimento = new AtendimentoRealizado();
        ProdutoAtendimentoUtilizado produto = new ProdutoAtendimentoUtilizado();

        produtoAtendimentoUtilizadoService.registrarProdutoUtilizado(List.of(produto), atendimento);

        assertEquals(atendimento, produto.getAtendimentoRealizado());
    }

    @Test
    void totalVendasNoMes_DeveRetornarProdutosVendidos() {
        ProdutoAtendimentoUtilizado produto1 = new ProdutoAtendimentoUtilizado();
        ProdutoAtendimentoUtilizado produto2 = new ProdutoAtendimentoUtilizado();

        when(produtoAtendimentoUtilizadoRepository.buscarTotalVendasNoMes(FinalidadeProdutoAtendimentoEnum.VENDIDO, 10, 2024))
                .thenReturn(List.of(produto1, produto2));

        List<ProdutoAtendimentoUtilizado> resultado = produtoAtendimentoUtilizadoService.totalVendasNoMes(10, 2024);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(produtoAtendimentoUtilizadoRepository, times(1))
                .buscarTotalVendasNoMes(FinalidadeProdutoAtendimentoEnum.VENDIDO, 10, 2024);
    }

    @Test
    void totalVendasNoMes_DeveRetornarListaVaziaSeNaoHouverProdutos() {
        when(produtoAtendimentoUtilizadoRepository.buscarTotalVendasNoMes(FinalidadeProdutoAtendimentoEnum.VENDIDO, 10, 2024))
                .thenReturn(List.of());

        List<ProdutoAtendimentoUtilizado> resultado = produtoAtendimentoUtilizadoService.totalVendasNoMes(10, 2024);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(produtoAtendimentoUtilizadoRepository, times(1))
                .buscarTotalVendasNoMes(FinalidadeProdutoAtendimentoEnum.VENDIDO, 10, 2024);
    }

    @Test
    void registrarProdutoUtilizado_DeveRetornarListaVazia_SeListaProdutosForVazia() {
        AtendimentoRealizado atendimento = new AtendimentoRealizado();

        List<ProdutoAtendimentoUtilizado> resultado = produtoAtendimentoUtilizadoService.registrarProdutoUtilizado(List.of(), atendimento);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(produtoAtendimentoUtilizadoRepository, never()).saveAll(any());
    }

    @Test
    void totalVendasNoMes_DeveLancarExcecao_SeMesForInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> produtoAtendimentoUtilizadoService.totalVendasNoMes(0, 2024));

        assertThrows(IllegalArgumentException.class,
                () -> produtoAtendimentoUtilizadoService.totalVendasNoMes(13, 2024));
    }

    @Test
    void totalVendasNoMes_DeveLancarExcecao_SeAnoForInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> produtoAtendimentoUtilizadoService.totalVendasNoMes(5, -1));
    }

    @Test
    void totalVendasNoMes_DeveLancarExcecao_SeRepositorioNaoInicializado() {
        ProdutoAtendimentoUtilizadoService serviceSemRepo = new ProdutoAtendimentoUtilizadoService(null);

        assertThrows(NullPointerException.class,
                () -> serviceSemRepo.totalVendasNoMes(5, 2024));
    }
}