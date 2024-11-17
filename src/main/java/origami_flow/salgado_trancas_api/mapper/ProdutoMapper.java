package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.ProdutoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ProdutoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.produto.ProdutoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    Produto toProdutoEntity(ProdutoRequestDTO produtoRequestDTO);

    Produto toProdutoEntity(ProdutoAtualizacaoRequestDTO produtoAtualizacaoRequestDTO);

    ProdutoDetalheResponseDTO toProdutoDetalheResponseDTO(Produto produto);
}
