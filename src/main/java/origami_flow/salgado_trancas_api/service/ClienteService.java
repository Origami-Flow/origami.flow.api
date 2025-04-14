package origami_flow.salgado_trancas_api.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import origami_flow.salgado_trancas_api.dto.CepDTO;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.entity.Servico;
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

    private final ImagemService imagemService;


    public List<Cliente> listarCliente() {
        return clienteRepository.findAll();
    }

    public Cliente clientePorId(Integer id) {
        return clienteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("cliente"));
    }

    private FileRequestDTO buildImagem(Cliente cliente, MultipartFile imagem) {
        return FileRequestDTO.builder()
              .name(Objects.requireNonNullElse(cliente.getNome(), "defaultName"))
              .file(imagem)
              .path("/clientes")
              .build();
    }

    public Cliente cadastrarCliente(Cliente cliente, String cep) {
        cliente.setEndereco(cadastrarEndereco(cep));
        if(clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail())) throw new EntidadeComConflitoException("cliente");
        cliente.setDataCriacao(LocalDate.now(ZoneOffset.of("-03:00")));
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Integer id, Cliente clienteAtualizado, MultipartFile imagem) {
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
        cliente.setImagem(Objects.nonNull(imagem) && !imagem.isEmpty() ? imagemService.uploadFile(buildImagem(clienteAtualizado, imagem)) : cliente.getImagem());

        return clienteRepository.save(cliente);
    }

    public void deletarCliente(Integer id) {
        if (!clienteRepository.existsById(id)) throw new EntidadeNaoEncontradaException("cliente ");
        clienteRepository.deleteById(id);
    }

    public Endereco cadastrarEndereco(String cep) {
        Endereco endereco = enderecoService.cadastrar(cep);
        return endereco;
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
