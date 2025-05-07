package origami_flow.salgado_trancas_api.dto.response;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaixaDetalheResponseDTO {

    @Schema(description = "Identificador único do caixa", example = "1")
    private Integer id;

    @Schema(description = "Data de fechamento do caixa", example = "2024-11-30")
    private LocalDate dataFechamento;

    @Schema(description = "Data de abertura do caixa", example = "2024-11-01")
    private LocalDate dataAbertura;

    @Schema(description = "Receita total registrada no caixa", example = "5000.00")
    private Double receitaTotal;

    @Schema(description = "Despesa total registrada no caixa", example = "1200.00")
    private Double despesaTotal;

    @Schema(description = "Informações do salão associado ao caixa")
    private SalaoDetalheResponseDTO salao;
}
