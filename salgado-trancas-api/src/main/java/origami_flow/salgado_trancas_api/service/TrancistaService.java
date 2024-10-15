package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrancistaService {

    private final TrancistaRepository trancistaRepository;

    public List<Trancista> listarTrancista() {
        return trancistaRepository.findAll();
    }

    public Trancista trancistaPorId(Integer id) {
        return trancistaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("trancista "));
    }

    public Trancista atualizarTrancista(Integer id, Trancista trancista){
        if (!trancistaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("trancista");
        trancista.setId(id);
        return trancistaRepository.save(trancista);
    }

    public void deletar(Integer id){
        if (!trancistaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("trancista ");
        trancistaRepository.deleteById(id);
    }
}
