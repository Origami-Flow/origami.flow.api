package origami_flow.salgado_trancas_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.entity.Cliente;

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

    @Schema(description = "Nota do atendimento", implementation = AvaliacaoResponseDTO.class)
    private AvaliacaoResponseDTO avaliacao;

    @Schema(description = "Cliente atendido", implementation = ClienteAtendimentoDTO.class)
    private ClienteAtendimentoDTO cliente;


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClienteAtendimentoDTO {

        private String nome;

        private String email;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AvaliacaoResponseDTO{

        private String comentario;

        private Double nota;

    }

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
