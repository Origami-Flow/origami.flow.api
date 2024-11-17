package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.repository.AvaliacaoClienteRepository;

@Service
@RequiredArgsConstructor
public class AvaliacaoClienteService {

    private final AvaliacaoClienteRepository avaliacaoClienteRepository;
}
