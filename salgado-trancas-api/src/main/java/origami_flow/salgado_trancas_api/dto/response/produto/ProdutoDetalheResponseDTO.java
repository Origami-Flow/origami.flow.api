package origami_flow.salgado_trancas_api.dto.response.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDetalheResponseDTO {

    private Integer id;

    private String nome;

    private String marca;

    private Double valorCompra;

    private Double valorVenda;

    private Integer quantidadeEmbalagem;

    private String unidadeMedida;

    private String funcionalidade;
}
