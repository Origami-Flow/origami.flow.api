package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Avaliacao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.AvaliacaoClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoClienteService {

    private final AvaliacaoClienteRepository avaliacaoClienteRepository;

    public List<Avaliacao> listarAaliacao(){
        return avaliacaoClienteRepository.findAll();
    }

    public Avaliacao avaliacaoPorId(Integer id){
        return avaliacaoClienteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("avaliacao"));
    }

    public Avaliacao criarAvaliacao(Avaliacao avaliacao){
        if (avaliacao.getAtendimentorealizado() == null || avaliacao.getNota() == null || avaliacao.getNota() < 0 ) throw new EntidadeComConflitoException();

        return avaliacaoClienteRepository.save(avaliacao);
    }

    public Avaliacao atualizarAvaliacao(Integer id, Avaliacao avaliacao){
        if (avaliacaoClienteRepository.existsById(id)) throw  new EntidadeNaoEncontradaException("avaliacao");
        avaliacao.setId(id);

        return avaliacaoClienteRepository.save(avaliacao);
    }

    public void deletarAvaliacao(Integer id){
        if (avaliacaoClienteRepository.existsById(id)) throw new EntidadeNaoEncontradaException("avaliacao");

        avaliacaoClienteRepository.deleteById(id);
    }
}
