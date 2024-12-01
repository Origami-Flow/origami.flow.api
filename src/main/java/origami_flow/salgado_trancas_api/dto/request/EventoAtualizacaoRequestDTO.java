package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventoAtualizacaoRequestDTO {

    @Schema(description = "Data e hora de início do evento",
            example = "2024-12-01T01:19:17.702Z",
            required = true)
    private LocalDateTime dataHoraInicio;

    @Schema(description = "Data e hora de término do evento",
            example = "2024-12-01T01:19:17.702Z",
            required = true)
    private LocalDateTime dataHoraTermino;

    @Schema(description = "Tipo do evento (ATENDIMENTO, CORTE, etc.)",
            example = "ATENDIMENTO",
            required = true)
    private TipoEventoEnum tipoEvento;

    @Schema(description = "Status do evento (CONCLUÍDO, PENDENTE, etc.)",
            example = "CONCLUÍDO",
            required = true)
    private StatusEventoEnum statusEvento;

    @Schema(description = "ID do serviço relacionado ao evento",
            example = "null",
            required = true)
    private Integer idServico;

    @Schema(description = "ID do trancista relacionado ao evento",
            example = "null",
            required = true)
    private Integer idTrancista;

    @Schema(description = "ID do auxiliar relacionado ao evento",
            example = "null",
            required = true)
    private Integer auxiliarId;
}
