package origami_flow.salgado_trancas_api.dto.response.cadastro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastroCriptografadoResponse {
    private Integer id;

    private String nome;

    private String email;

    private String senha;
}
