package origami_flow.salgado_trancas_api.dto.response;

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
    private Evento evento;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventoResponseDTO{

        private Integer id;

        private LocalDateTime dataHoraInicio;

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
