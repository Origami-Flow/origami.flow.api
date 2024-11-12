package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.request.EventoRequestDTO;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.mapper.EventoMapper;
import origami_flow.salgado_trancas_api.service.EventoService;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    private final EventoMapper eventoMapper;

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody EventoRequestDTO eventoRequestDTO) {
        Evento evento = eventoService.criarEvento(eventoMapper.toEntity(eventoRequestDTO),
                eventoRequestDTO.getClienteId(), eventoRequestDTO.getServicoId(), eventoRequestDTO.getTrancistaId());
        return ResponseEntity.ok(evento);
    }
}
