package origami_flow.salgado_trancas_api.dto.response.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ClienteDetalheResponseDTO {


    @Schema(description = "Identificador único do usuário", example = "1")
    private Integer id;


    @Schema(description = "Nome completo do usuário", example = "Jean Santos")
    private String nome;

    @Schema(description = "Endereço de e-mail do usuário", example = "jean.santos@example.com")
    private String email;


    @Schema(description = "Número de telefone do usuário", example = "(11) 98765-4321")
    private String telefone;

    @Schema(description = "Gênero do usuário", example = "MASCULINO")
    private GeneroEnum genero;

    @Schema(description = "Tipo de cabelo do usuário", example = "A4")
    private TipoCabeloEnum tipoCabelo;

    @Schema(description = "Cor do cabelo do usuário", example = "CASTANHO")
    private String corCabelo;


    @Schema(description = "Ocupação do usuário", example = "Estagiario")
    private String ocupacao;


    @Schema(description = "Endereço do usuário", implementation = EnderecoDetalheResponseDTO.class)
    private EnderecoDetalheResponseDTO endereco;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnderecoDetalheResponseDTO {
        @Schema(description = "Identificador único do endereço", example = "1")
        private Integer id;

        @Schema(description = "Código de Endereçamento Postal", example = "12345-678")
        private String cep;

        @Schema(description = "Unidade Federativa", example = "SP")
        private String uf;

        @Schema(description = "Nome da cidade", example = "São Paulo")
        private String cidade;

        @Schema(description = "Nome do bairro", example = "Jardins")
        private String bairro;

        @Schema(description = "Nome da via", example = "Avenida Paulista")
        private String logradouro;

        @Schema(description = "Número do endereço", example = "123")
        private Integer numero;

        @Schema(description = "Complemento do endereço", example = "Apto 45")
        private String complemento;
    }
}
