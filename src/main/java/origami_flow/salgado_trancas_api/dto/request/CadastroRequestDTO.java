package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.GeneroEnum;
import origami_flow.salgado_trancas_api.constans.TipoCabeloEnum;
import origami_flow.salgado_trancas_api.constans.UserRolesEnum;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroRequestDTO {

    @NotBlank
    @Schema(description = "Nome do usuário", example = "Jean Rocha")
    private String nome;

    @NotBlank
    @Schema(description = "Email do usuário", example = "jean.santos@example.com")
    private String email;

    @NotBlank
    @Size(min = 8, max = 128)
    @Schema(description = "Senha do usuário", example = "jean1234", minLength = 8, maxLength = 128)
    private String senha;

    @NotNull
    private UserRolesEnum role;

    @NotNull
    @Past
    @Schema(description = "Data de nascimento do usuário (deve ser uma data passada)", example = "2000-03-13")
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 11, max = 11)
    @Schema(description = "Telefone do usuário (formato: 11 dígitos)", example = "11940028922")
    private String telefone;

    @NotBlank
    @Schema(description = "Ocupação do usuário", example = "Estagiario")
    private String ocupacao;

    @NotBlank
    @Size(min = 8, max = 8)
    @Schema(description = "CEP do usuário (formato: 8 dígitos)", example = "01001000")
    private String cep;

    @Schema(description = "Gênero do usuário", example = "MASCULINO")
    private GeneroEnum genero;

    @Schema(description = "Tipo de cabelo do usuário", example = "A4")
    private TipoCabeloEnum tipoCabelo;

    @NotBlank
    @Schema(description = "Cor do cabelo do usuário", example = "Castanho escuro")
    private String corCabelo;
}
