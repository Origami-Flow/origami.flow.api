package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.ClienteMapper;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final EnderecoService enderecoService;

    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public Cliente clientePorId(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("cliente"));
    }

    public Cliente atualizarCliente(Integer id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) throw new EntidadeNaoEncontradaException("cliente ");
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Integer id) {
        if (!clienteRepository.existsById(id)) throw new EntidadeNaoEncontradaException("cliente ");
        clienteRepository.deleteById(id);
    }

    public Cliente cadastrarEndereco(Integer id, Endereco endereco) {
        Cliente cliente = clientePorId(id);
        cliente.setEndereco(enderecoService.cadastrar(endereco));
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarEndereco(Integer id, Endereco endereco) {
        Cliente cliente = clientePorId(id);
        Endereco enderecoAtualizado = enderecoService.atualizar(cliente.getEndereco().getId(), endereco);
        cliente.setEndereco(enderecoAtualizado);
        return clienteRepository.save(cliente);
    }
}
