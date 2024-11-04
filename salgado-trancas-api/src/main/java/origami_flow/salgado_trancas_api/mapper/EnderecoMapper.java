package origami_flow.salgado_trancas_api.mapper;

import com.gtbr.domain.Cep;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import origami_flow.salgado_trancas_api.dto.response.endereco.EnderecoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    @Mapping(target = "cidade", source = "localidade")
    Endereco toEnderecoEntity(Cep cep);

    EnderecoDetalheResponseDTO toEnderecoDetalheResponseDTO(Endereco endereco);
}
