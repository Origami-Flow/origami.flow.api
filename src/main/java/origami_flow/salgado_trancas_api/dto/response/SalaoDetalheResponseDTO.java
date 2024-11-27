package origami_flow.salgado_trancas_api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.entity.Endereco;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class  SalaoDetalheResponseDTO {

    @Schema(description = "Identificador único do salão", example = "1")
    private Integer id;

    @Schema(description = "Nome do salão", example = "Salgado tranças")
    private String nome;

    @Schema(description = "Endereço do salão", implementation = EnderecoDetalheResponseDTO.class)
    private EnderecoDetalheResponseDTO endereco;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnderecoDetalheResponseDTO {


        @Schema(description = "Identificador único do endereço", example = "1")
        private Integer id;

        @Schema(description = "Código de Endereçamento Postal (CEP)", example = "12345-678")
        private String cep;

        @Schema(description = "Unidade Federativa (UF)", example = "SP")
        private String uf;

        @Schema(description = "Cidade do endereço", example = "São Paulo")
        private String cidade;

        @Schema(description = "Bairro do endereço", example = "Jardins")
        private String bairro;

        @Schema(description = "Logradouro do endereço", example = "Avenida Paulista")
        private String logradouro;

        @Schema(description = "Número do endereço", example = "123")
        private Integer numero;

        @Schema(description = "Complemento do endereço", example = "Apto 45")
        private String complemento;
    }
}
