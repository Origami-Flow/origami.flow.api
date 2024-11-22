package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.response.AuxiliarDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Auxiliar;

@Mapper(componentModel = "spring")
public interface AuxiliarMapper {

    AuxiliarDetalheResponseDTO toDto(Auxiliar auxiliar);
}
