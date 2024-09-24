package origami_flow.salgado_trancas_api.mapper.usuario;

import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioTokenDto;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;

public class UsuarioDetailsService {

    public  static UsuarioTokenDto of(UsuarioAbstract usuario, String token){
        UsuarioTokenDto usuarioTokenDto= new UsuarioTokenDto();

        usuarioTokenDto.setUserId(usuario.getId());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }
}
