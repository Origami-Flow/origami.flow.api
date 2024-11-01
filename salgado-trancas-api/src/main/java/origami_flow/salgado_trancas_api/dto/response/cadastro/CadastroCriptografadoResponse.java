package origami_flow.salgado_trancas_api.dto.response.cadastro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroCriptografadoResponse {


    @Schema(description = "Identificador único do usuário", example = "1")
    private Integer id;


    @Schema(description = "Nome completo do usuário", example = "Jean Santos")
    private String nome;


    @Schema(description = "Endereço de e-mail do usuário", example = "jean.santos@example.com")
    private String email;


    @Schema(description = "Senha do usuário", example = "senhaSecreta123")
    private String senha;
}
