package jwt.api.service.usuario.dto;

import jwt.api.domain.Usuario;
import jwt.api.service.usuario.autenticacao.dto.JwtTokenResponse;

public class UsuarioMapper {

    public static Usuario of(CadastroRequestDTO cadastroRequestDTO) {
        return Usuario.builder()
                .nome(cadastroRequestDTO.getNome())
                .email(cadastroRequestDTO.getEmail())
                .senha(cadastroRequestDTO.getSenha())
                .build();
    }

    public static JwtTokenResponse of(Usuario usuario, String token) {
        return JwtTokenResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .token(token)
                .build();

    }
}
