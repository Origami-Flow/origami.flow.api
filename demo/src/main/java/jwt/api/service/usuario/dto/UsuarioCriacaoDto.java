package jwt.api.service.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class UsuarioCriacaoDto {

    @Size(min = 3, max = 12)
    @Schema(description = "Nome de usuário", example = "Fernando Brandão")
    private String nome;

    @Email
    @Schema(description = "Email do usuário", example = "fernando.brando@sptech.school")
    private String emaill;

    @Size(min = 6, max = 18 )
    @Schema(description = "Senha do usuário", example = "AdminAdmin")
    private String senha;
}
