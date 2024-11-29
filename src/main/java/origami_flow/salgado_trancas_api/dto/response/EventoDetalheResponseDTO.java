package origami_flow.salgado_trancas_api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class EventoDetalheResponseDTO {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraTermino;

    private TipoEventoEnum tipoEvento;

    private StatusEventoEnum statusEvento;

    private ClienteResponseDTO cliente;

    private ServicoResponseDTO servico;

    private TrancistaResponseDTO trancista;

    private AuxiliarResponseDTO auxiliar;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClienteResponseDTO {

        private Integer id;

        private String nome;

        private String telefone;

        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServicoResponseDTO {

        private Integer id;

        private String nome;

        private Double valorServico;

        private String descricao;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrancistaResponseDTO {

        private Integer id;

        private String nome;

        private String email;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuxiliarResponseDTO {
         private Integer id;

         private String nome;

         private String email;

         private Double valorMaoDeObra;
    }
}
