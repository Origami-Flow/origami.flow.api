package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

}
