package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.repository.UsuarioRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
public class CadastroService {
    @Autowired
    private UsuarioRepository clienteRepository;
    @Autowired
    private TrancistaRepository trancistaRepository;

    public Usuario cadastrarUsuario(Usuario cliente){
        cliente.setId_usuario(null);
        return clienteRepository.save(cliente);
    }

    public Trancista cadastrarTrancista(Trancista trancista) {
        trancista.setId_usuario(null);
        return trancistaRepository.save(trancista);
    }
}
