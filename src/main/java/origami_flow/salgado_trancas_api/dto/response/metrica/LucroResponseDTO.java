package origami_flow.salgado_trancas_api.dto.response.metrica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LucroResponseDTO {
    private Double lucroMes;
}
