package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
public class LoginService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TrancistaRepository trancistaRepository;

    public UsuarioAbstract autenticar(String email, String senha) {
        UsuarioAbstract usuarioEncontrado = clienteRepository.findByEmailAndSenha(email,senha);
        if (usuarioEncontrado != null) {
            return usuarioEncontrado;
        }
        usuarioEncontrado = trancistaRepository.findByEmailAndSenha(email,senha);
        if (usuarioEncontrado !=null) {
            return usuarioEncontrado;
        }
        throw new EntidadeNaoEncontradaException("Usuario");
    }
}
