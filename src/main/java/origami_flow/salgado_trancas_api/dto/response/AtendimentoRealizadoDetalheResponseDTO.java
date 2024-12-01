package origami_flow.salgado_trancas_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtendimentoRealizadoDetalheResponseDTO  {

    @Schema(description = "Identificador único do atendimento realizado", example = "1")
    private Integer id;

    @Schema(description = "Valor da receita gerada pelo atendimento", example = "100.50")
    private Double receita;

    @Schema(description = "Detalhes do evento relacionado ao atendimento", implementation = EventoResponseDTO.class)
    private EventoResponseDTO evento;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventoResponseDTO {

        @Schema(description = "Identificador único do evento", example = "2")
        private Integer id;

        @Schema(description = "Data e hora de início do evento", example = "2024-11-30T14:00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime dataHoraInicio;

        @Schema(description = "Data e hora de término do evento", example = "2024-11-30T16:00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime dataHoraTermino;

        @Schema(description = "Tipo de evento relacionado ao atendimento", example = "CONSULTA")
        private TipoEventoEnum tipoEvento;

        @Schema(description = "Status atual do evento", example = "CONCLUIDO")
        private StatusEventoEnum statusEvento;
    }
}
