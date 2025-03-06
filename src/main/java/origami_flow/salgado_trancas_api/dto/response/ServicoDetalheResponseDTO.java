package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicoDetalheResponseDTO {

    private Integer id;

    private String nome;

    private String descricao;

    private LocalTime tempoDuracao;

    private Double valorminimoServico;

    private Double valormaximoServico;

    private Double valorSinal;
}
