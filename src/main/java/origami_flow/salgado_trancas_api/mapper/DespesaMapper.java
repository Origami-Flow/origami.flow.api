package origami_flow.salgado_trancas_api.mapper;


import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.DespesaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.DespesaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Despesa;

@Mapper(componentModel = "spring")
public interface DespesaMapper {

    Despesa toDespesaEntity(DespesaRequestDTO despesaRequestDTO);

    DespesaDetalheResponseDTO toDespesaDetalheResponseDTO(Despesa despesa);
}
