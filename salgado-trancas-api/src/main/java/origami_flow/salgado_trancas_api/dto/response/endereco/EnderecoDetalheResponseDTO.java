package origami_flow.salgado_trancas_api.dto.response.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDetalheResponseDTO {

    @Schema(description = "Identificador único do endereço", example = "1")
    private Integer id;

    @Schema(description = "Código de Endereçamento Postal (CEP) do endereço", example = "12345-678")
    private String cep;

    @Schema(description = "Unidade Federativa (UF) do endereço", example = "SP")
    private String uf;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String cidade;

    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @Schema(description = "Logradouro do endereço (rua, avenida, etc.)", example = "Avenida Paulista")
    private String logradouro;

    @Schema(description = "Número do endereço", example = "100")
    private Integer numero;

    @Schema(description = "Complemento do endereço (apartamento, bloco, etc.)", example = "Apto 101")
    private String complemento;

}
