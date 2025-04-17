package origami_flow.salgado_trancas_api.dto.response.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private Integer id;

    private String nome;

    private String telefone;

    private String email;
}
