package jwt.api.service.usuario.autenticacao.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDto {
    private String email;
    private String senha;

}
