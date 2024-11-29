package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuxiliarAtualizacaoRequestDTO {

    private  String nome;

    private String email;

    @Positive
    private Double valorMaoDeObra;
}
