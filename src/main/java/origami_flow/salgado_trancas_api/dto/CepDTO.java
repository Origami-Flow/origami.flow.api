package origami_flow.salgado_trancas_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CepDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String uf;
    private String estado;
    private String regiao;
    @JsonProperty("localidade")
    private String cidade;
    private String complemento;
}
