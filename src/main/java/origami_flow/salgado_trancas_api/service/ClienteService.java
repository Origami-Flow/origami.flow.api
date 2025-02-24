package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
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
        if(clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail())) throw new EntidadeComConflitoException("cliente");
        cliente.setDataCriacao(LocalDate.now(ZoneOffset.of("-03:00")));
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteAtualizado) {
        Cliente cliente = clientePorId(id);
        cliente.setNome(clienteAtualizado.getNome() != null ? clienteAtualizado.getNome() : cliente.getNome());
        cliente.setEmail(clienteAtualizado.getEmail() != null ? clienteAtualizado.getEmail() : cliente.getEmail());
        cliente.setSenha(clienteAtualizado.getSenha() != null ? new BCryptPasswordEncoder().encode(clienteAtualizado.getSenha()) : cliente.getSenha());
        cliente.setTelefone(clienteAtualizado.getTelefone() != null ? clienteAtualizado.getTelefone() : cliente.getTelefone());
        cliente.setComprimentoCabelo(clienteAtualizado.getComprimentoCabelo() != null ? clienteAtualizado.getComprimentoCabelo() : cliente.getComprimentoCabelo());
        cliente.setDataNascimento(clienteAtualizado.getDataNascimento() != null ? clienteAtualizado.getDataNascimento() : cliente.getDataNascimento());
        cliente.setOcupacao(clienteAtualizado.getOcupacao() != null ? clienteAtualizado.getOcupacao() : cliente.getOcupacao());
        cliente.setGenero(clienteAtualizado.getGenero() != null ? clienteAtualizado.getGenero() : cliente.getGenero());
        cliente.setTipoCabelo(clienteAtualizado.getTipoCabelo() != null ? clienteAtualizado.getTipoCabelo() : cliente.getTipoCabelo());
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
        return clienteRepository.buscarPorEmail(email).orElse(null);
    }

    public Integer clientesNovosNoMes(int mes, int ano) {
        return clienteRepository.buscarNovosClientesNoMes(mes, ano);
    }
}
