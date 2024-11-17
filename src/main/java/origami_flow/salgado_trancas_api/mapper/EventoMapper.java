package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.EventoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.EventoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.EventoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Evento;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    Evento toEntity(EventoRequestDTO eventoRequestDTO);

    Evento toEntity(EventoAtualizacaoRequestDTO eventoAtualizacaoRequestDTO);

    EventoDetalheResponseDTO toDto(Evento evento);

}
