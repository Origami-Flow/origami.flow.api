package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.GeneroEnum;
import origami_flow.salgado_trancas_api.constans.TipoCabeloEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteAtualizacaoRequestDTO {

    @NotNull
    @Positive
    private Integer id;

    private String nome;

    private String email;

    private String senha;

    private String dataNascimento;

    private String telefone;

    private String ocupacao;

    private GeneroEnum genero;

    private TipoCabeloEnum tipoCabelo;

}
