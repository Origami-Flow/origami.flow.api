package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.response.metrica.MetricasResponseDTO;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricaService {

    private final ProdutoAtendimentoUtilizadoService produtoAtendimentoUtilizadoService;

    private final AtendimentoRealizadoService atendimentoRealizadoService;

    private final ClienteService clienteService;

    private final CaixaService caixaService;


    public MetricasResponseDTO buscarDadosParaMetrica(int mes, int ano) {
        return MetricasResponseDTO.builder()
                .vendasDoMes(contarTotalVendasNoMes(mes, ano))
                .agendamentosDoMes(atendimentoRealizadoService.buscarNumeroDeAtendimentoRealizado(mes, ano))
                .clientesNovosNoMes(clienteService.clientesNovosNoMes(mes, ano))
                .trancaMaisFeitaNoMes(atendimentoRealizadoService.trancaMaisRealizadaoNoMes(mes, ano))
                .taxaDeClienteQueAgendaramNoMes(calcularTaxaDeAgendamentoNoMes(mes, ano))
                .lucroDoMesAtual(calcularLucroDoMes(mes, ano))
                .lucroDoMesPassado(mes == 1 ? calcularLucroDoMes(12, ano-1) : calcularLucroDoMes(mes-1, ano))
                .build();
    }

    public int buscarTotcalDeVendasNoMes(int mes, int ano) {
        return contarTotalVendasNoMes(mes, ano);
    }

    public Double buscarLucrosDoMeS(int mes, int ano) {
        return calcularLucroDoMes(mes, ano);
    }


    private int contarTotalVendasNoMes(int mes, int ano) {
        int totalVendas = 0;
        List<ProdutoAtendimentoUtilizado> produtoAtendimentoUtilizados = produtoAtendimentoUtilizadoService.totalVendasNoMes(mes, ano);
        if (produtoAtendimentoUtilizados.isEmpty()) return totalVendas;
        for (ProdutoAtendimentoUtilizado produtoAtendimentoUtilizado : produtoAtendimentoUtilizados) {
           totalVendas += produtoAtendimentoUtilizado.getQuantidade();
        }
        return totalVendas;
    }

    private Double calcularTaxaDeAgendamentoNoMes(int mes, int ano) {
        Double novosClientes = (double) clienteService.clientesNovosNoMes(mes, ano);
        Double agendamentosDeNovosCliente = (double) atendimentoRealizadoService.buscarNumeroDeAtendimentoRealizadoComClientesNovos(mes, ano);
        if (novosClientes <= 0.0) return 0.0;
        return (agendamentosDeNovosCliente/novosClientes) * 100;
    }

    private Double calcularLucroDoMes(int mes, int ano) {
        Caixa caixa = caixaService.buscarCaixaPorMes(mes, ano);
        if (caixa == null) return 0.0;
        return caixa.getReceitaTotal() - caixa.getDespesaTotal();
    }
}
