package jwt.api.service.usuario.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioTokenDto {
    private Integer id;
    private String nome;
    private String email;
    private String token;

}
