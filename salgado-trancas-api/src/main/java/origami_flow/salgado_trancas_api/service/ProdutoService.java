package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ProdutoRepository;

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

    //Em revisao
    public Produto cadastrarProduto(Produto produto, Integer idSalao ,Integer quantidade){
        Salao salao = salaoService.salaoPorId(idSalao);
        if(produtoRepository.existsByNome(produto.getNome())) throw new EntidadeComConflitoException("produto");
        Produto produtoSalvo = produtoRepository.save(produto);
        Estoque estoque = Estoque.builder().quantidade(quantidade).produto(produtoSalvo).salao(salao).build();
        estoqueService.cadastraProdutoNoEstoque(estoque);
        return produtoSalvo;
    }

    public Produto atualizarProduto(Integer id, Produto produto){
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("Produto");
        produto.setId(id);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id){
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("Produto ");
        produtoRepository.deleteById(id);
    }
}
