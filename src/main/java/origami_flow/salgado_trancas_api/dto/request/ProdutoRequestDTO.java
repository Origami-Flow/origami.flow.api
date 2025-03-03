package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import origami_flow.salgado_trancas_api.constans.TipoProdutoEnum;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequestDTO {

    @NotBlank
    @Schema(description = "Nome do produto", example = "Shampoo Hidratante")
    private String nome;

    @NotBlank
    @Schema(description = "Marca do produto", example = "Marca XYZ")
    private String marca;

    @NotNull
    @Positive
    @Schema(description = "Valor de compra do produto", example = "25.99")
    private Double valorCompra;

    @NotNull
    @Positive
    @Schema(description = "Valor de venda do produto", example = "39.99")
    private Double valorVenda;

    @NotNull
    @Positive
    @Schema(description = "Quantidade por embalagem do produto", example = "1")
    private Integer quantidadeEmbalagem;

    @NotBlank
    @Schema(description = "Unidade de medida do produto", example = "Litros")
    private String unidadeMedida;

    @NotNull
    @Schema(description = "Finalidade do produto", example = "LOJA")
    private TipoProdutoEnum tipo;

    @NotNull
    @Positive
    @Schema(description = "Quantidade disponível do produto", example = "50")
    private Integer quantidade;

    @NotNull
    @Positive
    @Schema(description = "ID do salão associado ao produto", example = "1")
    private Integer idSalao;

    @Schema(description = "Imagem do produto")
    private MultipartFile imagem;
}
