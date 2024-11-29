package origami_flow.salgado_trancas_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoUtilizadoRequestDTO {

    private Integer id;

    private Integer quantidade;

    private FinalidadeProdutoAtendimentoEnum finalidade;
}
