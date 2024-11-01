package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoAtualizacaoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String marca;

    @NotNull
    @Positive
    private Double valorCompra;

    @NotNull
    @Positive
    private Double valorVenda;

    @NotNull
    @Positive
    private Integer quantidadeEmbalagem;

    @NotBlank
    private String unidadeMedida;

    @NotBlank
    private String funcionalidade;
}
