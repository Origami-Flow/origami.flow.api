package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.AgendaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.AgendaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Agenda;

@Mapper(componentModel = "spring")
public interface AgendaMapper {

    Agenda toEntity(AgendaRequestDTO agendaRequestDTO);

    AgendaDetalheResponseDTO toDetalheResponseDTO(Agenda agenda);
}
