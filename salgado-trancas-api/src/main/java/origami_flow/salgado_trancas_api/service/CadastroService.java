package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
public class CadastroService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrarUsuario(Cliente cliente){
        cliente.setId_usuario(null);
        return clienteRepository.save(cliente);
    }
}
