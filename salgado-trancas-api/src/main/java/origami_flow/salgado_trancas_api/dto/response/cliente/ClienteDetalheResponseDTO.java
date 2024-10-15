package origami_flow.salgado_trancas_api.dto.response.cliente;

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

    private Integer id;

    private String nome;

    private String email;

    private String telefone;

    private GeneroEnum genero;

    private TipoCabeloEnum tipoCabelo;

    private String corCabelo;

    private String ocupacao;

    private EnderecoDetalheResponseDTO endereco;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class EnderecoDetalheResponseDTO {
        private Integer id;

        private String cep;

        private String cidade;

        private  String logradouro;

        private Integer numero;

        private String complemento;
    }
}
