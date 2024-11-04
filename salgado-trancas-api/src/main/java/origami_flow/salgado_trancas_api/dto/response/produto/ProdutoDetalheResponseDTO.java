package origami_flow.salgado_trancas_api.dto.response.produto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDetalheResponseDTO {

    @Schema(description = "Identificador único do produto", example = "1")
    private Integer id;

    @Schema(description = "Nome do produto", example = "Shampoo")
    private String nome;

    @Schema(description = "Marca do produto", example = "Marca X")
    private String marca;

    @Schema(description = "Valor de compra do produto", example = "15.99")
    private Double valorCompra;

    @Schema(description = "Valor de venda do produto", example = "25.99")
    private Double valorVenda;

    @Schema(description = "Quantidade de itens por embalagem", example = "12")
    private Integer quantidadeEmbalagem;

    @Schema(description = "Unidade de medida do produto", example = "ml")
    private String unidadeMedida;

    @Schema(description = "Funcionalidade do produto", example = "Limpeza e hidratação")
    private String funcionalidade;
}
