package origami_flow.salgado_trancas_api.dto.response.metrica;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricasResponseDTO {

    @Schema(description = "Número de vendas realizadas no mês", example = "120")
    private Integer vendasDoMes;

    @Schema(description = "Número de agendamentos realizados no mês", example = "85")
    private Integer agendamentosDoMes;

    @Schema(description = "Número de clientes novos no mês", example = "30")
    private Integer clientesNovosNoMes;

    @Schema(description = "Trança mais realizada no mês", example = "Corte Reto")
    private String trancaMaisFeitaNoMes;

    @Schema(description = "Taxa de clientes que agendaram no mês", example = "75")
    private Double taxaDeClienteQueAgendaramNoMes;

    @Schema(description = "Lucro do mês atual", example = "5000.00")
    private Double lucroDoMesAtual;

    @Schema(description = "Lucro do mês passado", example = "4500.00")
    private Double lucroDoMesPassado;
}
