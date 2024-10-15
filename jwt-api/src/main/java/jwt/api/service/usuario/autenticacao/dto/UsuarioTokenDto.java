package jwt.api.service.usuario.autenticacao.dto;

import lombok.*;

@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioTokenDto {
    private Integer id;
    private String nome;
    private String email;
    private String token;

}
