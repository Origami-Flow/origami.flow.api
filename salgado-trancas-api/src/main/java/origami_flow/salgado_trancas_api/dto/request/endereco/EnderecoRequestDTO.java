package origami_flow.salgado_trancas_api.dto.request.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoRequestDTO {

    @NotBlank
    private String tipo;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

    @NotBlank
    private String cidade;

    @NotBlank
    private String logradouro;

    @NotNull
    private Integer numero;

    private String complemento;

    @NotBlank
    private String bairro;

    @NotBlank
    @Size(min = 2, max = 2)
    private String uf;
}
