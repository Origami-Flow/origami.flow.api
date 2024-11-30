package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinancaResponseDTO {

    private Double lucro;

    private DespesaFinancaResponseDTO despesas;

    private AtendimentoRealizadoFinancaResponseDTO atendimentos;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DespesaFinancaResponseDTO {
        private Double valor;

        private String nome;

        private String descricao;

        private LocalDate data;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Builder
    public  static class AtendimentoRealizadoFinancaResponseDTO{
        private Double valor;

        private String nome;

        private LocalDate data;
    }
}
