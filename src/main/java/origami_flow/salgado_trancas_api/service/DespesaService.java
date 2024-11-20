package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Despesa;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.DespesaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DespesaService {

    private final DespesaRepository despesaRepository;

    private final ProdutoService produtoService;

    private final CaixaService caixaService;

    public List<Despesa> listarTodasDepespesas(){
        return despesaRepository.findAll();
    }

    public Despesa despesaPorId(Integer id){
        return despesaRepository.findById(id).orElseThrow(EntidadeComConflitoException::new);
    }

    public Despesa cadastrarDespesa(Despesa despesa, Integer idProduto, Integer idCaixa){
        if (despesa.getProduto() != null) {
            Produto produto = produtoService.produtoPorId(idProduto);
            despesa.setProduto(produto);
            despesa.setValor(produto.getValorCompra());
        }
        Caixa caixa = caixaService.caixaPorId(idCaixa);
        despesa.setCaixa(caixa);
        return despesaRepository.save(despesa);
    }

    public Despesa atualizarDespesa(Integer id, Despesa despesa){
        if (!despesaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("despesa");
        despesa.setId(id);
        return despesaRepository.save(despesa);
    }

    public Double totalDespesasMensal(int ano, int mes){
        return despesaRepository.getTotalDespesaMensal(ano, mes);
    }
}
