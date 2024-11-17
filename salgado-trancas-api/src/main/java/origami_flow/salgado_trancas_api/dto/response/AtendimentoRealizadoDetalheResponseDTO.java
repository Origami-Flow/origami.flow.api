package origami_flow.salgado_trancas_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Evento;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtendimentoRealizadoDetalheResponseDTO {

    private Integer id;

    private Double receita;

    private EventoResponseDTO evento;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventoResponseDTO{

        private Integer id;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime dataHoraInicio;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime dataHoraTermino;

        private TipoEventoEnum tipoEvento;

        private StatusEventoEnum statusEvento;
    }
/*    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CaixaResponseDTO{


    }*/
}
