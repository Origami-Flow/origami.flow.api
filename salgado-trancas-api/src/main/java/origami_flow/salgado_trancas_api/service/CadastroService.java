package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final ClienteRepository clienteRepository;

    private final TrancistaRepository trancistaRepository;

    public Cliente cadastrarUsuario(Cliente cliente){
        if (clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail())) throw new EntidadeComConflitoException("telefone ou email ");
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Trancista cadastrarTrancista(Trancista trancista) {
        if (trancistaRepository.existsByEmail(trancista.getEmail())) throw new EntidadeComConflitoException("email");
        trancista.setId(null);
        return trancistaRepository.save(trancista);
    }
}
