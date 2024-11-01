package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaoRequestDTO {

    @NotBlank
    @Schema(description = "Nome do cliente ou responsável", example = "Jean Santos")
    private String nome;

    @NotBlank
    @Size(min = 8, max = 8)
    @Schema(description = "Código postal (CEP) do endereço", example = "12345678")
    private String cep;
}
