package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.CaixaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.CaixaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Caixa;

@Mapper(componentModel = "spring")
public interface CaixaMapper {

    Caixa toCaixaEntity(CaixaRequestDTO caixaRequestDTO);

    CaixaDetalheResponseDTO toCaixaDetalheResponseDTO(Caixa caixa);
}
