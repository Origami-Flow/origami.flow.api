package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.TipoProdutoEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoAtualizacaoRequestDTO {

    @NotBlank
    @Schema(description = "Nome do produto", example = "Shampoo")
    private String nome;

    @NotBlank
    @Schema(description = "Marca do produto", example = "Marca X")
    private String marca;

    @NotNull
    @Positive
    @Schema(description = "Valor de compra do produto", example = "15.99")
    private Double valorCompra;

    @NotNull
    @Positive
    @Schema(description = "Valor de venda do produto", example = "25.99")
    private Double valorVenda;

    @NotNull
    @Positive
    @Schema(description = "Quantidade de itens por embalagem", example = "12")
    private Integer quantidadeEmbalagem;

    @NotBlank
    @Schema(description = "Unidade de medida do produto", example = "ml")
    private String unidadeMedida;

    @NotNull
    @Schema(description = "Finalidade do produto", example = "LOJA")
    private TipoProdutoEnum tipo;
}
