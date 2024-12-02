package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
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

    @Schema(description = "Data e hora de início do evento",
            example = "2024-12-01T01:19:17.702Z",
            required = true)
    @NotNull
    private LocalDateTime dataHoraInicio;

    @Schema(description = "Data e hora de término do evento",
            example = "2024-12-01T01:19:17.702Z",
            required = true)
    @NotNull
    private LocalDateTime dataHoraTermino;

    @Schema(description = "Tipo do evento (CONSULTA, CORTE, etc.)",
            example = "ATENDIMENTO",
            required = true)
    @NotNull
    private TipoEventoEnum tipoEvento;

    @Schema(description = "ID do cliente relacionado ao evento",
            example = "null")
    private Integer clienteId;

    @Schema(description = "ID do serviço relacionado ao evento",
            example = "null")

    private Integer servicoId;

    @Schema(description = "ID do trancista relacionado ao evento",
            example = "null")
    private Integer trancistaId;

    @Schema(description = "ID do auxiliar relacionado ao evento",
            example = "null")
    private Integer auxiliarId;
}
