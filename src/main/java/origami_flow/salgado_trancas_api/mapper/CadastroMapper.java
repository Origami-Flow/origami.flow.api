package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;

@Mapper(componentModel = "spring")
public interface CadastroMapper {

    Cliente toEntity(CadastroRequestDTO cadastroRequestDTO);
}

