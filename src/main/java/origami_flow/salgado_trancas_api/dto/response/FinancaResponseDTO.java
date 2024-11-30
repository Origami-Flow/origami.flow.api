package origami_flow.salgado_trancas_api.dto.response;

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
    private Double lucro;
    private List<DespesaDetalhadaDTO> despesas;
    private List<AtendimentoDetalhadoDTO> atendimentos;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DespesaDetalhadaDTO {
        private String nome;
        private String descricao;
        private Double valor;
        private LocalDate data;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AtendimentoDetalhadoDTO {
        private String nomeCliente;
        private String tipoAtendimento;
        private LocalDate data;
        private Double valor;
    }
}
