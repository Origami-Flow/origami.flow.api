package origami_flow.salgado_trancas_api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoAtualizacaoRequestDTO {

    private LocalDateTime dataHoraInicio;

    private LocalDateTime dataHoraTermino;

    private TipoEventoEnum tipoEvento;
    private StatusEventoEnum statusEvento;

    private Integer idServico;

    private Integer idTrancista;

    private Integer auxiliarId;
}
