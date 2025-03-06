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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventoDetalheResponseDTO {

    @Schema(description = "Identificador único do evento", example = "1")
    private Integer id;

    @Schema(description = "Data e hora de início do evento", example = "2024-11-01T10:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraInicio;

    @Schema(description = "Data e hora de término do evento", example = "2024-11-01T12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraTermino;

    @Schema(description = "Valor cobrado do cliente", example = "120,00")
    private Double valorCobrado;

    @Schema(description = "Tipo de evento", example = "Corte")
    private TipoEventoEnum tipoEvento;

    @Schema(description = "Status do evento", example = "CONCLUIDO")
    private StatusEventoEnum statusEvento;

    @Schema(description = "Informações do cliente relacionado ao evento")
    private ClienteResponseDTO cliente;

    @Schema(description = "Informações do serviço relacionado ao evento")
    private ServicoResponseDTO servico;

    @Schema(description = "Informações do trancista relacionado ao evento")
    private TrancistaResponseDTO trancista;

    @Schema(description = "Informações do auxiliar relacionado ao evento")
    private AuxiliarResponseDTO auxiliar;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClienteResponseDTO {

        @Schema(description = "Identificador único do cliente", example = "1")
        private Integer id;

        @Schema(description = "Nome do cliente", example = "João Silva")
        private String nome;

        @Schema(description = "Telefone do cliente", example = "11999999999")
        private String telefone;

        @Schema(description = "E-mail do cliente", example = "joao.silva@email.com")
        private String email;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServicoResponseDTO {

        @Schema(description = "Identificador único do serviço", example = "1")
        private Integer id;

        @Schema(description = "Nome do serviço", example = "Corte de cabelo")
        private String nome;

        @Schema(description = "Valor minimo do serviço", example = "50.00")
        private Double valorMinimoServico;

        @Schema(description = "Valor máximo do serviço", example = "150.00")
        private Double valorMaximoServico;

        @Schema(description = "Descrição do serviço", example = "Corte simples de cabelo")
        private String descricao;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TrancistaResponseDTO {

        @Schema(description = "Identificador único do trancista", example = "1")
        private Integer id;

        @Schema(description = "Nome do trancista", example = "Maria Oliveira")
        private String nome;

        @Schema(description = "E-mail do trancista", example = "maria.oliveira@email.com")
        private String email;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuxiliarResponseDTO {

        @Schema(description = "Identificador único do auxiliar", example = "1")
        private Integer id;

        @Schema(description = "Nome do auxiliar", example = "Carlos Souza")
        private String nome;

        @Schema(description = "E-mail do auxiliar", example = "carlos.souza@email.com")
        private String email;

        @Schema(description = "Valor da mão de obra do auxiliar", example = "30.00")
        private Double valorMaoDeObra;
    }
}
