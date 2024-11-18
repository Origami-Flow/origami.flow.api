package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.SalaoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalaoService {

    private final SalaoRepository salaoRepository;

    private final EnderecoService enderecoService;

    public Salao cadastrarSalao(Salao salao, String cep) {
        Endereco endereco = enderecoService.cadastrar(cep);
        if (salaoRepository.existsByNome(salao.getNome())) throw new EntidadeComConflitoException("salão");
        salao.setEndereco(endereco);
        return salaoRepository.save(salao);
    }

    public List<Salao> listar() {
        return salaoRepository.findAll();
    }

    public Salao salaoPorId(Integer id) {
        return salaoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("salão"));
    }

    public Salao atualizar(Integer id, Salao salao) {
        if (!salaoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("salão");
        salao.setId(id);
        return salaoRepository.save(salao);
    }

    public Salao atualizarEndereco(Integer idSalao, String cep) {
        Salao salao = salaoPorId(idSalao);
        if (salao == null) throw new EntidadeNaoEncontradaException("salão");

        Endereco endereco = enderecoService.atualizar(salao.getEndereco().getId(), cep);
        salao.setEndereco(endereco);
        return salaoRepository.save(salao);
    }

    public void deletarSalao(Integer id) {
        if (!salaoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("salao");
        salaoRepository.deleteById(id);
    }
}
