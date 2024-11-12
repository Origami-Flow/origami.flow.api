package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.repository.EventoRepository;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    private final ClienteService clienteService;

    private final TrancistaService trancistaService;

    public Evento criarEvento(Evento evento, Integer clienteId, Integer servicoId, Integer trancistaId) {
        Trancista trancista = trancistaService.trancistaPorId(trancistaId);
        Cliente cliente = clienteService.clientePorId(clienteId);
        evento.setCliente(cliente);
        evento.setTrancista(trancista);
        return null;
    }
}
