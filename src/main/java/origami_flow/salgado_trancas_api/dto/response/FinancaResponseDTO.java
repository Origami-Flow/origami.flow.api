package origami_flow.salgado_trancas_api.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancaResponseDTO {

    @Schema(description = "Lucro total calculado", example = "5000.00")
    private Double lucro;

    @Schema(description = "Lista de despesas detalhadas")
    private List<DespesaDetalhadaDTO> despesas;

    @Schema(description = "Lista de atendimentos detalhados")
    private List<AtendimentoDetalhadoDTO> atendimentos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DespesaDetalhadaDTO {

        @Schema(description = "Nome da despesa", example = "Aluguel")
        private String nome;

        @Schema(description = "Descrição da despesa", example = "Pagamento do aluguel mensal")
        private String descricao;

        @Schema(description = "Valor da despesa", example = "1200.00")
        private Double valor;

        @Schema(description = "Data da despesa", example = "2024-11-01")
        private LocalDate data;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AtendimentoDetalhadoDTO {

        @Schema(description = "Nome do cliente atendido", example = "João Silva")
        private String nomeCliente;

        @Schema(description = "Tipo de atendimento realizado", example = "Corte de cabelo")
        private String tipoAtendimento;

        @Schema(description = "Data do atendimento", example = "2024-11-01")
        private LocalDate data;

        @Schema(description = "Valor do atendimento", example = "50.00")
        private Double valor;
    }
}
