package origami_flow.salgado_trancas_api.dto.response.estoque;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.TipoProdutoEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDetalheResponseDTO {

    @Schema(description = "Identificador único do estoque", example = "1")
    private Integer id;

    @Schema(description = "Quantidade de produtos no estoque", example = "50")
    private Integer quantidade;

    @Schema(description = "Detalhes do produto associado ao estoque", implementation = ProdutoDetalheResponseDTO.class)
    private ProdutoDetalheResponseDTO produto;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProdutoDetalheResponseDTO {

        @Schema(description = "Identificador único do produto", example = "1")
        private Integer id;

        @Schema(description = "Nome do produto", example = "Shampoo")
        private String nome;

        @Schema(description = "Marca do produto", example = "Marca X")
        private String marca;

        @Schema(description = "Quantidade de itens por embalagem", example = "12")
        private Integer quantidadeEmbalagem;

        @Schema(description = "Unidade de medida do produto", example = "ml")
        private String unidadeMedida;

        @Schema(description = "Valor de compra do produto", example = "15.99")
        private Double valorCompra;

        @Schema(description = "Valor de venda do produto", example = "25.99")
        private Double valorVenda;

        @Schema(description = "Finalidade do produto", example = "LOJA")
        private TipoProdutoEnum tipo;

        private String imagemUrl;
    }
}