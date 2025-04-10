package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import origami_flow.salgado_trancas_api.dto.request.ServicoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ServicoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.ServicoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Servico;

@Mapper(componentModel = "spring")
public interface ServicoMapper {

    Servico toEntity(ServicoRequestDTO servicoRequestDTO);

    Servico toEntity(ServicoAtualizacaoRequestDTO servicoAtualizacaoRequestDTO);

    @Mapping(source = "servico.imagem.url", target = "imagemUrl")
    ServicoDetalheResponseDTO toResponseDTO(Servico servico);

}
