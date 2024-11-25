package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;

public class JwtTokenMapper {
    public static JwtTokenResponse jwtTokenResponse(Cliente cliente, String token) {
        return JwtTokenResponse.builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .email(cliente.getEmail())
                .token(token)
                .build();
    }

    public static JwtTokenResponse jwtTokenResponse(Trancista trancista, String token) {
        return JwtTokenResponse.builder()
                .id(trancista.getId())
                .nome(trancista.getNome())
                .email(trancista.getEmail())
                .token(token)
                .build();
    }
}
