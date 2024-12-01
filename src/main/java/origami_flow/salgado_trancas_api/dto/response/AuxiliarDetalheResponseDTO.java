package origami_flow.salgado_trancas_api.dto.response;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuxiliarDetalheResponseDTO {

    @Schema(description = "Identificador único do auxiliar", example = "1")
    private Integer id;

    @Schema(description = "Nome do auxiliar", example = "Daniel Silva")
    private String nome;

    @Schema(description = "Email do auxiliar", example = "Daniel.silva@example.com")
    private String email;

    @Schema(description = "Valor da mão de obra do auxiliar", example = "50.00")
    private Double valorMaoDeObra;
}
