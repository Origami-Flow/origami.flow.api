package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.UserRolesEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrancistaRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotNull
    private UserRolesEnum role;

    @NotBlank
    private String telefone;

    @NotBlank
    private String tipo;
}
