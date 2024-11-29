package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Despesa;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.DespesaRepository;

import java.time.LocalDate;
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
          if (idProduto != null) {
            Produto produto = produtoService.produtoPorId(idProduto);
            despesa.setProduto(produto);
            despesa.setValor(produto.getValorCompra());
        }
        Caixa caixa = caixaService.caixaPorId(idCaixa);
        despesa.setCaixa(caixa);
        caixa.setDespesaTotal(caixa.getDespesaTotal() + despesa.getValor());


        return despesaRepository.save(despesa);
    }

    public Despesa atualizarDespesa(Integer id, Despesa despesa, Integer idProduto, Integer idCaixa){
        Despesa despesaAtualizar = despesaPorId(id);
        despesaAtualizar.setId(id);
        despesaAtualizar.setProduto(idProduto != null? produtoService.produtoPorId(idProduto):despesaAtualizar.getProduto());
        despesaAtualizar.setCaixa(idCaixa != null? caixaService.caixaPorId(idCaixa):despesaAtualizar.getCaixa());
        despesaAtualizar.setData(despesa.getData() != null? despesa.getData():despesaAtualizar.getData());
        despesaAtualizar.setDescricao(despesa.getDescricao() != null? despesa.getDescricao():despesaAtualizar.getDescricao());

        return despesaRepository.save(despesaAtualizar);
    }

    public void deletarDespesa(Integer id){
        if (!despesaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("despesa");
        despesaRepository.deleteById(id);
    }

    public Double totalDespesasMensal(LocalDate inicio, LocalDate fim){
        return despesaRepository.getTotalDespesaMensal(inicio, fim);
    }
}
