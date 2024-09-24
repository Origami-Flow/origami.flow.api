package origami_flow.salgado_trancas_api.dto.autenticacao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginDto {
    private String email;
    private String senha;

}
