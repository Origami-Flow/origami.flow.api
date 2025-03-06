package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import origami_flow.salgado_trancas_api.dto.CepDTO;
import origami_flow.salgado_trancas_api.dto.response.endereco.EnderecoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Endereco;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEnderecoEntity(CepDTO cepDTO);

    EnderecoDetalheResponseDTO toEnderecoDetalheResponseDTO(Endereco endereco);
}
