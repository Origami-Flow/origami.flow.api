package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
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

    private final PesquisaBinaria pesquisaBinaria;

    public List<Produto> listarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Produto produtoPorId(Integer id){
        return produtoRepository.findById(id).orElseThrow(EntidadeNaoEncontradaException::new);
    }

    public Produto cadastrarProduto(Produto produto, Integer idSalao ,Integer quantidade){
        Salao salao = salaoService.salaoPorId(idSalao);
        if(produtoRepository.existsByNome(produto.getNome())) throw new EntidadeComConflitoException("produto");
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
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("produto ");
        produtoRepository.deleteById(id);
    }

    public Produto buscarProdutoNome(String nome) {
        List<Produto> produtos = produtoRepository.findAllByOrderByNome();
        Lista<Produto> listaProdutos = new Lista<>();
        produtos.forEach(listaProdutos::add);
        Produto produtoEncontrado = pesquisaBinaria.buscarProdutoPorNome(listaProdutos, nome);
        if( produtoEncontrado == null) throw new EntidadeNaoEncontradaException("produto");
        return produtoEncontrado;
    }
}
