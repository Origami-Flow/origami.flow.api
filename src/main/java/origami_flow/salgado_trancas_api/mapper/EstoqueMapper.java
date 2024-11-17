package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.response.estoque.EstoqueDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Estoque;

@Mapper(componentModel = "spring")
public interface EstoqueMapper {

    EstoqueDetalheResponseDTO toEstoqueDetalheResponseDTO(Estoque estoque);
}
