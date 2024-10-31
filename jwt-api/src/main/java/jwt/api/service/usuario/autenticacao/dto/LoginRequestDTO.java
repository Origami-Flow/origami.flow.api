package jwt.api.service.usuario.autenticacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginRequestDTO {

    @Schema(description = "E-mail do usuário", example = "john@doe.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "123456")
    private String senha;
}
