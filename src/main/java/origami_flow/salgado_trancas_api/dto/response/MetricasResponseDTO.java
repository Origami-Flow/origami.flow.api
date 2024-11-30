package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricasResponseDTO {

    private Integer vendasDoMes;

    private Integer agendamentosDoMes;

    private Integer clientesNovosNoMes;

    private String trancaMaisFeitaNoMes;

    private Integer taxaDeClienteQueAgendaramNoMes;

    private Double lucroDoMesAtual;

    private Double lucroDoMesPassado;
}
