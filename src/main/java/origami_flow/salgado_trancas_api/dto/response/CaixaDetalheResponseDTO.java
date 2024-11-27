package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaixaDetalheResponseDTO {

    private Integer id;

    private LocalDateTime dataFechamento;

    private Double receitaTotal;

    private Double despesaTotal;

    private SalaoDetalheResponseDTO salao;

}
