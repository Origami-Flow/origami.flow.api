package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.dto.response.produto.ProdutoDetalheResponseDTO;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DespesaDetalheResponseDTO {

    private Integer id;

    private String nome;

    private Double valor;

    private LocalDate data;

    private CaixaDetalheResponseDTO caixa;

    private ProdutoDetalheResponseDTO produto;

}


