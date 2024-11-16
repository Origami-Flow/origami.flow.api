package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.response.AtendimentoRealizadoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;

@Mapper(componentModel = "spring")
public interface AtendimentoRealizadoMapper {

    AtendimentoRealizadoDetalheResponseDTO toAtendimentoRealizadoEntity(AtendimentoRealizado atendimentoRealizado);
}
