package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.CaixaRepository;

import javax.swing.text.html.Option;
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

    public Caixa cadastrarCaixa(Caixa caixa, Integer idSalao){
        Salao salao = salaoService.salaoPorId(idSalao);
        caixa.setSalao(salao);
        Caixa caixaSalvo = caixaRepository.save(caixa);

        return caixaSalvo;
    }

    public Caixa atualizarCaixa(Integer id, Caixa caixa){
        Optional<Caixa> caixaAtualizado = caixaRepository.findById(id);
        if (caixaAtualizado.isEmpty()) throw new EntidadeNaoEncontradaException("caixa");

        caixa.setId(id);
        return caixaRepository.save(caixa);
    }

    public void deletarCaixa(Integer id){
        if (!caixaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("caixa");
        caixaRepository.deleteById(id);
    }

}
