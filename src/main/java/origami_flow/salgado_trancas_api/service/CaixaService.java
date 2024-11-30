package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.CaixaRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

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
        caixa.setDataAbertura(LocalDate.now(ZoneOffset.of("-03:00")));
        caixa.setDataFechamento(null);
        caixa.setReceitaTotal(0.0);
        caixa.setDespesaTotal(0.0);

        return caixaRepository.save(caixa);
    }

    public Caixa atualizarCaixa(Integer id, Caixa caixa, Integer idSalao){
        Caixa caixaAtualizado = caixaPorId(id);
        caixaAtualizado.setId(id);
        caixaAtualizado.setSalao(idSalao != null? salaoService.salaoPorId(idSalao):caixaAtualizado.getSalao());
        caixaAtualizado.setDataAbertura(caixa.getDataAbertura() != null? caixa.getDataAbertura(): caixaAtualizado.getDataAbertura());
        caixaAtualizado.setDataFechamento(caixa.getDataFechamento() != null? caixa.getDataFechamento(): caixaAtualizado.getDataFechamento());
        caixaAtualizado.setDespesaTotal(caixa.getDespesaTotal() != null? caixa.getDespesaTotal(): caixaAtualizado.getDespesaTotal());
        caixaAtualizado.setReceitaTotal(caixa.getReceitaTotal() != null? caixa.getReceitaTotal(): caixaAtualizado.getReceitaTotal());
        return caixaRepository.save(caixaAtualizado);
    }

    public void deletarCaixa(Integer id){
        if (!caixaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("caixa");
        caixaRepository.deleteById(id);
    }


    public Caixa buscarCaixaPorMes(int mes, int ano){
        return caixaRepository.buscarCaixaPorMes(mes, ano).orElse(null);
    }
}
