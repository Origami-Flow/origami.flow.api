package mapper;

import domain.Usuario;
import org.mapstruct.Mapper;
import service.usuario.autenticacao.dto.UsuarioTokenDto;
import service.usuario.dto.UsuarioCriacaoDto;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toUsuarioEntity(UsuarioCriacaoDto usuarioCriacaoDto);

    UsuarioTokenDto toTokenDto(Usuario usuario, String token);

}
