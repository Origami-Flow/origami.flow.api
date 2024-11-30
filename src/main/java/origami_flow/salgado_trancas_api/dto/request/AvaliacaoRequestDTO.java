package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoRequestDTO {

    private String comentario;

    private Double nota;

    private Integer clienteId;

    private Integer salaoId;

    private Integer atendimentoRealizadoId;

}
