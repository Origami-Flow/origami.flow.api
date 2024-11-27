package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;

import java.util.List;

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

    public Cliente cadastrarCliente(Cliente cliente) {
        clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail());
        return clienteRepository.save(cliente);
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

    public Cliente cadastrarEndereco(Integer id, String cep) {
        Cliente cliente = clientePorId(id);
        cliente.setEndereco(enderecoService.cadastrar(cep));
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarEndereco(Integer idCliente, String cep) {
        Cliente cliente = clientePorId(idCliente);
        Endereco enderecoAtualizado = enderecoService.atualizar(cliente.getEndereco().getId(), cep);
        cliente.setEndereco(enderecoAtualizado);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarPorNome(String nome) {
        return clienteRepository.buscarPorNome(nome);
    }

    public Cliente buscarPorEmail(String email) {
        return clienteRepository.buscarPorEmail(email).orElseThrow(() -> new EntidadeNaoEncontradaException("cliente"));
    }
}
