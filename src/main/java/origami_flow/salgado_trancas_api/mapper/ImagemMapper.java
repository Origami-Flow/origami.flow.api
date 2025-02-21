package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import origami_flow.salgado_trancas_api.dto.FileDTO;
import origami_flow.salgado_trancas_api.entity.Imagem;

@Mapper(componentModel = "spring")
public interface ImagemMapper {

    @Mapping(source = "displayName", target = "nome")
    @Mapping(source = "assetFolder", target = "path")
    @Mapping(target = "createdAt", ignore = true)
    Imagem toEntity(FileDTO fileDTO);

}
