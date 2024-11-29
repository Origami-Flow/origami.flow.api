package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuxiliarRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @Positive
    private Double valorMaoDeObra;
}
