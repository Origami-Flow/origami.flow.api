package origami_flow.salgado_trancas_api.dto.request.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.constans.ComprimentoCabeloEnum;
import origami_flow.salgado_trancas_api.constans.GeneroEnum;
import origami_flow.salgado_trancas_api.constans.TipoCabeloEnum;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 128)
    private String senha;

    private String tokenGoogle;

    @NotNull
    @Past
    private LocalDate dtNascimento;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    private GeneroEnum genero;

    @NotBlank
    private String ocupacao;

    private TipoCabeloEnum tipoCabelo;

    private String corCabelo;

    private ComprimentoCabeloEnum comprimentoCabelo;

    private boolean progressiva;

    private boolean primeiraTranca;
}