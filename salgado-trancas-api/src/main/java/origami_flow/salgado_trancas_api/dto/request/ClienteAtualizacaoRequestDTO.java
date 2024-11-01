package origami_flow.salgado_trancas_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class ClienteAtualizacaoRequestDTO {

    @NotNull
    @Positive
    @Schema(description = "Identificador único do usuário", example = "1")
    private Integer id;

    @Schema(description = "Nome do usuário", example = "Jean Rocha")
    private String nome;

    @Schema(description = "Email do usuário", example = "jean.santos@example.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "jean1234")
    private String senha;

    @Schema(description = "Data de nascimento do usuário no formato 'YYYY-MM-DD'", example = "2000-03-13")
    private String dataNascimento;

    @Schema(description = "Telefone do usuário", example = "(11) 94002-8922")
    private String telefone;

    @Schema(description = "Ocupação do usuário", example = "Estagiario")
    private String ocupacao;

    @Schema(description = "Gênero do usuário", example = "MASCULINO")
    private GeneroEnum genero;

    @Schema(description = "Tipo de cabelo do usuário", example = "4A")
    private TipoCabeloEnum tipoCabelo;

}
