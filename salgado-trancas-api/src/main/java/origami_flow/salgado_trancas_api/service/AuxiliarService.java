package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Auxiliar;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.AuxiliarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuxiliarService {

    private final AuxiliarRepository auxiliarRepository;

    public List<Auxiliar> listar() {
        return auxiliarRepository.findAll();
    }

    public Auxiliar auxiliarPorId(Integer id) {
        return auxiliarRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("auxiliar"));
    }
}
