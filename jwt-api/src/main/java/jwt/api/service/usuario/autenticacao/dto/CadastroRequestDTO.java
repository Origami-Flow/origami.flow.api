package jwt.api.service.usuario.autenticacao.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroRequestDTO {

    @NotBlank
    @Schema(description = "Nome do usuário", example = "Paula")
    private String nome;

    @Schema(description = "Email do usuário", example = "paula.salgado@gmail.com")
    private String email;

    @Size(min = 8, max = 128)
    @Schema(description = "Senha do usuário", example = "12345678")
    private String senha;
}