package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public Cliente listarClientePorId(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Integer id, Cliente cliente) {
        if (clienteRepository.existsById(id)) {
            cliente.setId_usuario(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public void deletarCliente(Integer id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
        }
        throw new EntidadeNaoEncontradaException();
    }
}
