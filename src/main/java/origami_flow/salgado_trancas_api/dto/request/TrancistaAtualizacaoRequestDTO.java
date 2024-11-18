package origami_flow.salgado_trancas_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrancistaAtualizacaoRequestDTO {

    private String nome;

    private String email;

    private String telefone;

    private String senha;

    private String tipo;
}
