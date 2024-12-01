package origami_flow.salgado_trancas_api.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.dto.response.produto.ProdutoDetalheResponseDTO;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDetalheResponseDTO {

    @Schema(description = "Identificador único da despesa", example = "1")
    private Integer id;

    @Schema(description = "Nome da despesa", example = "Pomada")
    private String nome;

    @Schema(description = "Descrição da despesa", example = "Compra de material")
    private String descricao;

    @Schema(description = "Valor da despesa", example = "150.00")
    private Double valor;

    @Schema(description = "Data da despesa", example = "2024-11-01")
    private LocalDate data;

    @Schema(description = "Informações do caixa associado à despesa")
    private CaixaDetalheResponseDTO caixa;

    @Schema(description = "Informações do produto associado à despesa")
    private ProdutoDetalheResponseDTO produto;
}
