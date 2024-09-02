package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
public class LoginService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TrancistaRepository trancistaRepository;

    public Usuario autenticar(String email,String senha) {
        Usuario usuarioEncontrado = clienteRepository.findByEmailAndSenha(email,senha);
        if (usuarioEncontrado != null) {
            return usuarioEncontrado;
        }
        usuarioEncontrado = trancistaRepository.findByEmailAndSenha(email,senha);
        if (usuarioEncontrado !=null) {
            return usuarioEncontrado;
        }
        return null;
    }
}
