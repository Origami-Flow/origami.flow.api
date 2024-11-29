package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.CaixaRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CaixaService {

    private final CaixaRepository caixaRepository;
    private final SalaoService salaoService;


    public List<Caixa> listarTodosCaixas(){
        return caixaRepository.findAll();
    }

    public Caixa caixaPorId(Integer id){
        return caixaRepository.findById(id).orElseThrow(EntidadeComConflitoException::new);
    }

    public Caixa abrirCaixa( Integer idSalao){
        Caixa caixa = new Caixa();
        Salao salao = salaoService.salaoPorId(idSalao);
        caixa.setSalao(salao);
        caixa.setSalao(salao);

        return caixaRepository.save(caixa);
    }

    public Caixa atualizarCaixa(Integer id, Caixa caixa, Integer idSalao){
        Caixa caixaAtualizado = caixaPorId(id);
        caixaAtualizado.setSalao(idSalao != null? salaoService.salaoPorId(idSalao):caixaAtualizado.getSalao());
        caixaAtualizado.setDataAbertura(caixa.getDataAbertura() != null? caixa.getDataAbertura(): caixaAtualizado.getDataAbertura());
        caixaAtualizado.setDataFechamento(caixa.getDataFechamento() != null? caixa.getDataFechamento(): caixaAtualizado.getDataFechamento());
        caixaAtualizado.setDespesaTotal(caixa.getDespesaTotal() != null? caixa.getDespesaTotal(): caixaAtualizado.getDespesaTotal());
        caixaAtualizado.setReceitaTotal(caixa.getReceitaTotal() != null? caixa.getReceitaTotal(): caixaAtualizado.getReceitaTotal());
        return caixaRepository.save(caixa);
    }

    public void deletarCaixa(Integer id){
        if (!caixaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("caixa");
        caixaRepository.deleteById(id);
    }

}
