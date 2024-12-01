package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.EstoqueRepository;
import origami_flow.salgado_trancas_api.utils.ExportCsv;
import origami_flow.salgado_trancas_api.utils.Ordenacao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public void cadastrarProdutoNoEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public List<Estoque> listarProdutosEmEstoque() {
        return estoqueRepository.findAll();
    }

    public Estoque estoquePorId(Integer id) {
        return estoqueRepository.findByProdutoId(id).orElseThrow(() -> new EntidadeNaoEncontradaException("produto"));
    }

    public Estoque atualizarEstoque(Integer id, Integer quantidade) {
        Estoque produto = estoquePorId(id);
        if(produto.getQuantidade() >= 0) {
            produto.setQuantidade(produto.getQuantidade() + quantidade);
        }else {
            produto.setQuantidade(produto.getQuantidade() - quantidade);
        }
        return estoqueRepository.save(produto);
    }

    public void removerDoEstoque(Integer id) {
        if (!estoqueRepository.existsById(id)) throw new EntidadeNaoEncontradaException("produto");
        estoqueRepository.deleteById(id);
    }

    public List<Estoque> ordenar() {
        List<Estoque> lista = listarProdutosEmEstoque();
        Ordenacao.quickSortValorCompra(lista, 0, lista.size()-1);
        return lista;
    }

    public void exportar(List<Estoque> estoque) {
        ExportCsv.exportar(estoque);
    }
}
