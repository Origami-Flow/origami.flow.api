package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.exceptions.RequisicaoErradaException;
import origami_flow.salgado_trancas_api.repository.ProdutoRepository;
import origami_flow.salgado_trancas_api.utils.Lista;
import origami_flow.salgado_trancas_api.utils.PesquisaBinaria;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final EstoqueService estoqueService;

    private final SalaoService salaoService;

    public List<Produto> listarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Produto produtoPorId(Integer id){
        return produtoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("produto"));
    }

    public Produto cadastrarProduto(Produto produto, Integer idSalao ,Integer quantidade){
        Salao salao = salaoService.salaoPorId(idSalao);
        if(produtoRepository.existsByNome(produto.getNome())) throw new EntidadeComConflitoException("produto");
        if(quantidade == null || quantidade < 0) throw new RequisicaoErradaException("Quantidade de produtos inválida");
        Produto produtoSalvo = produtoRepository.save(produto);
        Estoque estoque = Estoque.builder().quantidade(quantidade).produto(produtoSalvo).salao(salao).build();
        estoqueService.cadastrarProdutoNoEstoque(estoque);
        return produtoSalvo;
    }

    public Produto atualizarProduto(Integer id, Produto produto){
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("produto");
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id){
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("produto");
        produtoRepository.deleteById(id);
    }

    public List<Produto> buscarProdutoNome(String nome) {
        return produtoRepository.findAllByOrderByNome(nome);
    }

    public List<Produto> listarTodosPorId(List<Integer> ids) {
        return produtoRepository.findAllById(ids);
    }
}
