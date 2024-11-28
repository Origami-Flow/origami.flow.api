package origami_flow.salgado_trancas_api.dto.response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Salao;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AvaliacaoDetalheResponseDTO {

    private Integer id;

    private String comentario;

    @NotNull
    private Double nota;

    @NotNull
    private ClienteAvaliacaoResponseDTO cliente;

    @NotNull
    private SalaoAvaliacaoResponseDTO salao;

    @NotNull
    private AtendimentoRealizado atendimentorealizado;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class ClienteAvaliacaoResponseDTO {
        private Integer id;

        private String nome;

        private String telefone;

        private String email;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class SalaoAvaliacaoResponseDTO{
        private Integer id;

        private String nome;
    }

}
