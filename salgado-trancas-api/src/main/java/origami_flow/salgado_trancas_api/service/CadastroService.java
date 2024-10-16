package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final ClienteService clienteService;

    private final TrancistaRepository trancistaRepository;

    public Cliente cadastrarCliente(Cliente cliente, String cep){
        Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
        return clienteService.cadastrarEndereco(clienteCadastrado.getId(), cep);
    }

    public Trancista cadastrarTrancista(Trancista trancista) {
        if (trancistaRepository.existsByEmail(trancista.getEmail())) throw new EntidadeComConflitoException("email");
        trancista.setId(null);
        return trancistaRepository.save(trancista);
    }
}
