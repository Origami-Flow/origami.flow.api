package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.SalaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.SalaoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Salao;

@Mapper(componentModel = "spring")
public interface SalaoMapper {

    Salao toSalaoEntity(SalaoRequestDTO salaoRequestDTO);

    SalaoDetalheResponseDTO toSalaoDetalheResponseDTO(Salao salao);
}
