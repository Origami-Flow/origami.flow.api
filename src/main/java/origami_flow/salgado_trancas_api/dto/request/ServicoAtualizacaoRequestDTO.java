package origami_flow.salgado_trancas_api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicoAtualizacaoRequestDTO {

    private String nome;

    private String descricao;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime tempoDuracao;

    private Double valorServico;

    private Double valorSinal;
}
