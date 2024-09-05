package origami_flow.salgado_trancas_api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiLivroDto {
    List<LivroDto> items;
}
