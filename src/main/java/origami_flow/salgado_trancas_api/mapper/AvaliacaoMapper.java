package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.AvaliacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.AvaliacaoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Avaliacao;

@Mapper(componentModel = "spring")
public interface AvaliacaoMapper {

    AvaliacaoDetalheResponseDTO toAvaliacaoDetalheResponseDTO(Avaliacao avaliacao);
    Avaliacao toEntity(AvaliacaoRequestDTO avaliacaoRequestDTO);
}
