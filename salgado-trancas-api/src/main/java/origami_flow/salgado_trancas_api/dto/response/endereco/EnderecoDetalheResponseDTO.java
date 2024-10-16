package origami_flow.salgado_trancas_api.dto.response.endereco;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDetalheResponseDTO {

    private Integer id;

    private String cep;

    private String uf;

    private String cidade;

    private String bairro;

    private  String logradouro;

    private Integer numero;

    private String complemento;

}
