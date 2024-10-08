package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.endereco.EnderecoRequestDTO;
import origami_flow.salgado_trancas_api.entity.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEnderecoEntity(EnderecoRequestDTO enderecoRequestDTO);
}
