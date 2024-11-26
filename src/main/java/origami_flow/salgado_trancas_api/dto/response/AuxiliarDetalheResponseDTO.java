package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuxiliarDetalheResponseDTO {

    private Integer id;

    private String nome;

    private String email;

    private Double valorMaoDeObra;
}
