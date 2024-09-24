package origami_flow.salgado_trancas_api.mapper.usuario;

import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioCriacaoDto;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioTokenDto;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;

public class UsuarioMapper {
    public static Cliente of(UsuarioCriacaoDto usuarioCriacaoDto){
        Cliente usuario = new Cliente();

        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());

        return usuario;
    }
    public static UsuarioTokenDto of(UsuarioAbstract Usuario, String token){
        UsuarioTokenDto usuario = new UsuarioTokenDto();

        usuario.setUserId(Usuario.getId());
        usuario.setEmail(Usuario.getEmail());
        usuario.setNome(Usuario.getNome());
        usuario.setToken(token);

        return usuario;
    }
}
