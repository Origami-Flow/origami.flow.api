package origami_flow.salgado_trancas_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroClienteSimplesRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String telefone;

    @NotBlank
    private String email;
}
