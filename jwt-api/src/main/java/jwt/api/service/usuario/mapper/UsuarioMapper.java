package jwt.api.service.usuario.mapper;

import jwt.api.domain.Cliente;
import jwt.api.domain.Trancista;
import jwt.api.service.usuario.autenticacao.dto.CadastroRequestDTO;
import jwt.api.service.usuario.autenticacao.dto.JwtTokenResponse;

public class UsuarioMapper {

    public static Cliente of(CadastroRequestDTO cadastroRequestDTO) {
        return Cliente.builder()
                .nome(cadastroRequestDTO.getNome())
                .email(cadastroRequestDTO.getEmail())
                .senha(cadastroRequestDTO.getSenha())
                .build();
    }

    public static JwtTokenResponse of(Cliente cliente, String token) {
        return JwtTokenResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .senha(cliente.getSenha())
                .token(token)
                .build();

    }

    public static JwtTokenResponse of(Trancista trancista, String token) {
        return JwtTokenResponse.builder()
                .id(trancista.getId())
                .nome(trancista.getNome())
                .email(trancista.getEmail())
                .senha(trancista.getSenha())
                .token(token)
                .build();

    }

}
