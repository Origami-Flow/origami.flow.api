package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

import java.util.List;

@Service
public class TrancistaService {
    @Autowired
    private TrancistaRepository trancistaRepository;

    public List<Trancista> listarTrancista() {
        return trancistaRepository.findAll();
    }

    public Trancista cadastrar(Trancista trancista) {
        trancista.setId_trancista(null);
        return trancistaRepository.save(trancista);
    }
}
