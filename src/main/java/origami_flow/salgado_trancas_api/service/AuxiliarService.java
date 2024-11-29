package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Auxiliar;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
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

    public List<Auxiliar> listarPorNome(String nome) {
        return auxiliarRepository.buscarPorNome(nome);
    }

    public Auxiliar cadastrar(Auxiliar auxiliar) {
        if (auxiliarRepository.existsByEmail(auxiliar.getEmail())) throw new EntidadeComConflitoException("auxiliar");
        return auxiliarRepository.save(auxiliar);
    }

    public Auxiliar atualizar(Integer id ,Auxiliar auxiliarAtualizacao) {
        Auxiliar auxiliar = auxiliarPorId(id);
        auxiliar.setNome(auxiliarAtualizacao.getNome() != null ? auxiliarAtualizacao.getNome(): auxiliar.getNome());
        auxiliar.setEmail(auxiliarAtualizacao.getEmail() != null ? auxiliarAtualizacao.getEmail(): auxiliar.getEmail());
        auxiliar.setValorMaoDeObra(auxiliarAtualizacao.getValorMaoDeObra() != null ? auxiliarAtualizacao.getValorMaoDeObra(): auxiliar.getValorMaoDeObra());
        return auxiliarRepository.save(auxiliar);
    }
}
