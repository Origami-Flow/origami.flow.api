package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.entity.Endereco;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SalaoDetalheResponseDTO {

    private Integer id;

    private String nome;

    private EnderecoDetalheResponseDTO endereco;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnderecoDetalheResponseDTO {
        private Integer id;

        private String cep;

        private String uf;

        private String cidade;

        private String bairro;

        private  String logradouro;

        private Integer numero;

        private String complemento;
    }
}
