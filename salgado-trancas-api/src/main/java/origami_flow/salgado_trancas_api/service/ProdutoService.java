package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public List<Produto> listarTodosProdutos(){

        return produtoRepository.findAll();
    }
    public Produto adicionarProduto(Produto produto){
        produto.setId(null);
        return produtoRepository.save(produto);
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
