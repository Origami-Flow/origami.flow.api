package jwt.api.service.usuario.autenticacao.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtTokenResponse {

    private Integer id;

    private String nome;

    private String email;

    private String senha;

    private String token;
}
