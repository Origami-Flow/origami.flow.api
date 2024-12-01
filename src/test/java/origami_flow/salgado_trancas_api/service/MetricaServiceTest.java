package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.dto.response.MetricasResponseDTO;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MetricaServiceTest {

    @Mock
    private ProdutoAtendimentoUtilizadoService produtoAtendimentoUtilizadoService;

    @Mock
    private AtendimentoRealizadoService atendimentoRealizadoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private CaixaService caixaService;

    @InjectMocks
    private MetricaService metricaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buscarDadosParaMetrica_DeveRetornarDadosCorretos() {
        int mes = 5;
        int ano = 2024;

        when(produtoAtendimentoUtilizadoService.totalVendasNoMes(mes, ano)).thenReturn(List.of(
                criarProdutoUtilizado(10), criarProdutoUtilizado(5)
        ));
        when(atendimentoRealizadoService.buscarNumeroDeAtendimentoRealizado(mes, ano)).thenReturn(15);
        when(clienteService.clientesNovosNoMes(mes, ano)).thenReturn(3);
        when(atendimentoRealizadoService.buscarNumeroDeAtendimentoRealizadoComClientesNovos(mes, ano)).thenReturn(2);
        when(atendimentoRealizadoService.trancaMaisRealizadaoNoMes(mes, ano)).thenReturn("Trança Box");
        when(caixaService.buscarCaixaPorMes(mes, ano)).thenReturn(criarCaixa(2000.0, 500.0));
        when(caixaService.buscarCaixaPorMes(4, ano)).thenReturn(criarCaixa(1500.0, 300.0));

        MetricasResponseDTO response = metricaService.buscarDadosParaMetrica(mes, ano);

        assertNotNull(response);
        assertEquals(15, response.getAgendamentosDoMes());
        assertEquals(15, response.getVendasDoMes());
        assertEquals(3, response.getClientesNovosNoMes());
        assertEquals(66.66666666666666, response.getTaxaDeClienteQueAgendaramNoMes());
        assertEquals(1500.0, response.getLucroDoMesAtual());
        assertEquals(1200.0, response.getLucroDoMesPassado());
        assertEquals("Trança Box", response.getTrancaMaisFeitaNoMes());
    }

    @Test
    void contarTotalVendasNoMes_DeveRetornarZero_SeNaoHouverVendas() {
        int mes = 5;
        int ano = 2024;

        when(produtoAtendimentoUtilizadoService.totalVendasNoMes(mes, ano)).thenReturn(List.of());

        MetricasResponseDTO response = metricaService.buscarDadosParaMetrica(mes, ano);

        assertEquals(0, response.getVendasDoMes());
    }

    @Test
    void calcularLucroDoMes_DeveRetornarZero_SeCaixaNaoExistir() {
        int mes = 5;
        int ano = 2024;

        when(caixaService.buscarCaixaPorMes(mes, ano)).thenReturn(null);

        MetricasResponseDTO response = metricaService.buscarDadosParaMetrica(mes, ano);

        assertEquals(0.0, response.getLucroDoMesAtual());
    }

    @Test
    void calcularTaxaDeAgendamentoNoMes_DeveRetornarZero_SeNaoHouverClientesNovos() {
        int mes = 5;
        int ano = 2024;

        when(clienteService.clientesNovosNoMes(mes, ano)).thenReturn(0);
        when(atendimentoRealizadoService.buscarNumeroDeAtendimentoRealizadoComClientesNovos(mes, ano)).thenReturn(0);

        MetricasResponseDTO response = metricaService.buscarDadosParaMetrica(mes, ano);

        assertEquals(0, response.getTaxaDeClienteQueAgendaramNoMes());
    }

    @Test
    void calcularLucroDoMesPassado_DeveCalcularCorretamenteParaJaneiro() {
        int mes = 1;
        int ano = 2024;

        when(caixaService.buscarCaixaPorMes(12, 2023)).thenReturn(criarCaixa(1800.0, 500.0));

        MetricasResponseDTO response = metricaService.buscarDadosParaMetrica(mes, ano);

        assertEquals(1300.0, response.getLucroDoMesPassado());
    }

    private ProdutoAtendimentoUtilizado criarProdutoUtilizado(int quantidade) {
        ProdutoAtendimentoUtilizado produto = new ProdutoAtendimentoUtilizado();
        produto.setQuantidade(quantidade);
        return produto;
    }

    private Caixa criarCaixa(double receitaTotal, double despesaTotal) {
        Caixa caixa = new Caixa();
        caixa.setReceitaTotal(receitaTotal);
        caixa.setDespesaTotal(despesaTotal);
        return caixa;
    }
}
