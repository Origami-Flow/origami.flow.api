package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.response.FinancaResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.MetricasResponseDTO;


@Service
@RequiredArgsConstructor
public class FinancaService {

    private final AtendimentoRealizadoService atendimentoRealizadoService;

    private final DespesaService despesaService;

    /*public FinancaResponseDTO buscarDadosParaFinancas(int mes, int ano) {


        return FinancaResponseDTO.builder()
                .atendimentos()
                .despesas()
                .lucro()
                .build();
    }*/
}
