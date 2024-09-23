package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.repository.AvaliacaoClienteRepository;

@Service
public class AvaliacaoClienteService {
    @Autowired
    private AvaliacaoClienteRepository avaliacaoClienteRepository;
}
