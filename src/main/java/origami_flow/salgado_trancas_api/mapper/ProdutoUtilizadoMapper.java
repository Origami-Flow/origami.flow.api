package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.request.ProdutoUtilizadoRequestDTO;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;

public class ProdutoUtilizadoMapper {

    public static ProdutoAtendimentoUtilizado toEntity(ProdutoUtilizadoRequestDTO produto, Produto produtoRegistrado) {
        return ProdutoAtendimentoUtilizado.builder()
                .id(produto.getId())
                .finalidade(produto.getFinalidade())
                .quantidade(produto.getQuantidade())
                .produto(produtoRegistrado)
                .build();
    }
}
