package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoRequestDTO {

    @NotNull
    private LocalDateTime dataHoraInicio;

    @NotNull
    private LocalDateTime dataHoraTermino;

    @NotNull
    private TipoEventoEnum tipoEvento;

    private Integer clienteId;

    private Integer servicoId;

    private Integer trancistaId;

    private Integer auxiliarId;
}
