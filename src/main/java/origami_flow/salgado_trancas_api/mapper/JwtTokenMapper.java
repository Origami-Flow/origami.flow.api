package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;

public class JwtTokenMapper {
    public static JwtTokenResponse jwtTokenResponse(UsuarioAbstract usuario, String token, String authorities) {
        return JwtTokenResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .token(token)
                .authorities(authorities)
                .build();
    }

}
