package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.TrancistaAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.TrancistaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.trancista.TrancistaDetalheResposeDTO;
import origami_flow.salgado_trancas_api.entity.Trancista;

@Mapper(componentModel = "spring")
public interface TrancistaMapper {

    Trancista toEntity(TrancistaRequestDTO trancistaRequestDTO);

    Trancista toEntity(TrancistaAtualizacaoRequestDTO trancistaAtualizacaoRequestDTOtDTO);

    TrancistaDetalheResposeDTO toDTO(Trancista trancista);
}
