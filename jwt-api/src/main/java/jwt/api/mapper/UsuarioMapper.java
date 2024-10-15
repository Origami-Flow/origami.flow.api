package jwt.api.mapper;

import jwt.api.domain.Usuario;
import jwt.api.service.usuario.autenticacao.dto.UsuarioTokenDto;
import jwt.api.service.usuario.dto.UsuarioCriacaoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuarioEntity(UsuarioCriacaoDto usuarioCriacaoDto);

    UsuarioTokenDto toTokenDto(Usuario usuario, String token);

}
