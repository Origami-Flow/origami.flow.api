package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrancistaService {

    private final TrancistaRepository trancistaRepository;

    public Trancista cadastrarTrancista(Trancista trancista){
        if (trancistaRepository.existsByEmailOrTelefone(trancista.getEmail(), trancista.getTelefone())) throw new EntidadeComConflitoException("trancista");
        return trancistaRepository.save(trancista);
    }

    public List<Trancista> listarTrancista() {
        return trancistaRepository.findAll();
    }

    public Trancista trancistaPorId(Integer id) {
        return trancistaRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("trancista "));
    }

    public Trancista atualizarTrancista(Integer id, Trancista trancistaAtualizado) {
        Trancista trancista = trancistaPorId(id);
        trancista.setNome(trancistaAtualizado.getNome() != null ? trancistaAtualizado.getNome() : trancista.getNome());
        trancista.setEmail(trancistaAtualizado.getEmail() != null ? trancistaAtualizado.getEmail() : trancista.getEmail());
        trancista.setTelefone(trancistaAtualizado.getTelefone() != null ? trancistaAtualizado.getTelefone() : trancista.getTelefone());
        trancista.setSenha(trancistaAtualizado.getSenha() != null ? new BCryptPasswordEncoder().encode(trancistaAtualizado.getPassword()) : trancista.getSenha());
        trancista.setTipo(trancistaAtualizado.getTipo() != null ? trancistaAtualizado.getTipo() : trancista.getTipo());
        return trancistaRepository.save(trancista);
    }

    public void deletar(Integer id){
        if (!trancistaRepository.existsById(id)) throw new EntidadeNaoEncontradaException("trancista ");
        trancistaRepository.deleteById(id);
    }

    public Trancista buscarPorEmail(String email) {
        return trancistaRepository.buscarPorEmail(email).orElse(null);
    }
}
