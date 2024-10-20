package origami_flow.salgado_trancas_api.dto.request;

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
    private String nome;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;
}
