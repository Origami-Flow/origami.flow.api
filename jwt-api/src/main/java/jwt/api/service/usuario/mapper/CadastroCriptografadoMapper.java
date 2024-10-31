package jwt.api.service.usuario.mapper;

import jwt.api.service.usuario.autenticacao.dto.CadastroCriptografado;
import jwt.api.service.usuario.autenticacao.dto.CadastroRequestDTO;

public class CadastroCriptografadoMapper {
    public static CadastroCriptografado of(CadastroRequestDTO cadastroRequestDTO) {
        return CadastroCriptografado.builder()
                .nome(cadastroRequestDTO.getNome())
                .email(cadastroRequestDTO.getEmail())
                .build();
    }
}
