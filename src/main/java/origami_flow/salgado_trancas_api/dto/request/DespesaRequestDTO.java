package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.entity.Caixa;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DespesaRequestDTO {

    @NotNull
    @Positive
    private Integer id;

    private String nome;

    private Double valor;

    private LocalDate data;

    private Integer idCaixa;

    private Integer idProduto;
}
