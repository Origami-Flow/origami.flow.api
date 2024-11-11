package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
    @PastOrPresent
    private LocalDateTime dataHoraInicio;

    @NotNull
    @FutureOrPresent
    private LocalDateTime dataHoraFim;

    @NotNull
    private TipoEventoEnum tipo;

    @NotNull
    private Integer clienteId;

    @NotNull
    private Integer servicoId;

    @NotNull
    private Integer dataAgendadaId;
}
