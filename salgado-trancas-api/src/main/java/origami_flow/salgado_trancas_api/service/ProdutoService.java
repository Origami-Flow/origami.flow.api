package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodosProdutos(){

        return produtoRepository.findAll();
    }
    public Produto adicionarProduto(Produto produto){
        produto.setId_produto(null);
        return produtoRepository.save(produto);
    }
    public Produto atualizarProduto(Integer id, Produto produto){
        if (!produtoRepository.existsById(id)){
            return null;
        }

        produto.setId_produto(id);
        return produtoRepository.save(produto);
    }
    public Boolean deletarProduto(Integer id){
        if (!produtoRepository.existsById(id)){
            return false;
        }
        produtoRepository.deleteById(id);
        return true;
    }
}
