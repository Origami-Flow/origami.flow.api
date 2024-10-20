package origami_flow.salgado_trancas_api.dto.response.estoque;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDetalheResponseDTO {

    private Integer id;

    private Integer quantidade;

    private ProdutoDetalheResponseDTO produto;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProdutoDetalheResponseDTO {

        private Integer id;

        private String nome;

        private String marca;

        private Integer quantidadeEmbalagem;

        private String unidadeMedida;

        private Double valorCompra;

        private Double valorVenda;

        private String funcionalidade;
    }
}