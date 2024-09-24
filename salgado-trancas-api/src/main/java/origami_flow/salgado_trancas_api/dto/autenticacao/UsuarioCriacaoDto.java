package origami_flow.salgado_trancas_api.dto.autenticacao;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCriacaoDto {

    @Schema(description = "Nome do usuário", example = "Fernando Brandão")
    private String nome;

    @Email
    @Schema(description = "Email do usuário", example = "fernando.brandao@sptech.school")
    private String email;

    @Size(min = 6, max = 16)
    @Schema(description = "Senha do usu[ario", example = "AdminAdmin")
    private String senha;
}
