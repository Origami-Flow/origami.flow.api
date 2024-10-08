package origami_flow.salgado_trancas_api.dto.request.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import origami_flow.salgado_trancas_api.constans.ComprimentoCabeloEnum;
import origami_flow.salgado_trancas_api.constans.GeneroEnum;
import origami_flow.salgado_trancas_api.constans.TipoCabeloEnum;

import java.time.LocalDate;

@Data
@Builder
public class ClienteRequestDTO {
    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 14)
    private String senha;

    private String token_google;

    @NotNull
    @Past
    private LocalDate dtNascimento;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    private GeneroEnum genero;

    private TipoCabeloEnum tipoCabelo;

    @NotBlank
    private String corCabelo;

    private ComprimentoCabeloEnum comprimentoCabelo;

    private boolean progressiva;

    private boolean primeiraTranca;

    @NotBlank
    private String ocupacao;

}
