package origami_flow.salgado_trancas_api.dto.response;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDetalheResponseDTO {

    @Schema(description = "Identificador único da avaliação", example = "1")
    private Integer id;

    @Schema(description = "Comentário da avaliação", example = "Excelente atendimento e qualidade do serviço")
    private String comentario;

    @NotNull
    @Schema(description = "Nota da avaliação", example = "5.0")
    private Double nota;

    @NotNull
    @Schema(description = "Informações do cliente que realizou a avaliação")
    private ClienteAvaliacaoResponseDTO cliente;

    @NotNull
    @Schema(description = "Informações do salão que foi avaliado")
    private SalaoAvaliacaoResponseDTO salao;

    @NotNull
    @Schema(description = "Informações do atendimento realizado relacionado à avaliação")
    private AtendimentoRealizadoResponseDTO atendimentoRealizado;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ClienteAvaliacaoResponseDTO {

        @Schema(description = "Identificador único do cliente", example = "1")
        private Integer id;

        @Schema(description = "Nome do cliente", example = "Jean Rocha")
        private String nome;

        @Schema(description = "Telefone do cliente", example = "(11) 94002-8922")
        private String telefone;

        @Schema(description = "Email do cliente", example = "jean.rocha@example.com")
        private String email;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SalaoAvaliacaoResponseDTO {

        @Schema(description = "Identificador único do salão", example = "1")
        private Integer id;

        @Schema(description = "Nome do salão", example = "Salgado Tranças")
        private String nome;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AtendimentoRealizadoResponseDTO {

        @Schema(description = "Identificador único do atendimento realizado", example = "1")
        private Integer id;

        @Schema(description = "Receita gerada no atendimento realizado", example = "150.0")
        private Double receita;
    }
}
