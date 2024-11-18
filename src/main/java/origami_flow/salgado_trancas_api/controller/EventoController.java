package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.dto.request.EventoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.EventoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.EventoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.mapper.EventoMapper;
import origami_flow.salgado_trancas_api.service.EventoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService eventoService;

    private final EventoMapper eventoMapper;

    @GetMapping
    public ResponseEntity<List<EventoDetalheResponseDTO>> listarEventos() {
        List<Evento> lista = eventoService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(eventoMapper::toDto).toList());
    }

    @GetMapping("/data")
    public ResponseEntity<List<EventoDetalheResponseDTO>> listarEventosPorData(@RequestParam("data") LocalDate data) {
        List<Evento> lista  = eventoService.listarPorData(data);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(eventoMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDetalheResponseDTO> buscarEventoPorId(@PathVariable Integer id) {
        Evento evento = eventoService.eventoPorId(id);
        return ResponseEntity.ok(eventoMapper.toDto(evento));
    }

    @PostMapping
    public ResponseEntity<EventoDetalheResponseDTO> criarEvento(@RequestBody EventoRequestDTO eventoRequestDTO) {
        Evento evento = eventoService.criarEvento(
                eventoMapper.toEntity(eventoRequestDTO),
                eventoRequestDTO.getClienteId(),
                eventoRequestDTO.getServicoId(),
                eventoRequestDTO.getTrancistaId(),
                eventoRequestDTO.getAuxiliarId()
        );
        return ResponseEntity.created(null).body(eventoMapper.toDto(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDetalheResponseDTO> atualizarEvento(@RequestBody EventoAtualizacaoRequestDTO eventoAtualizacaoRequestDTO, @PathVariable Integer id) {
        Evento evento = eventoService.atualizarEvento(
                eventoMapper.toEntity(eventoAtualizacaoRequestDTO),
                id,
                eventoAtualizacaoRequestDTO.getIdServico(),
                eventoAtualizacaoRequestDTO.getIdTrancista(),
                eventoAtualizacaoRequestDTO.getAuxiliarId()
        );
        return ResponseEntity.ok(eventoMapper.toDto(evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEvento(@PathVariable Integer id) {
        eventoService.apagarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/status/evento/{id}")
    public ResponseEntity<EventoDetalheResponseDTO> atualizarStatusEvento(@RequestParam StatusEventoEnum statusEvento, @PathVariable Integer id){
         Evento eventoAtualizado = eventoService.atualizarStatus(id,statusEvento);
         return ResponseEntity.ok(eventoMapper.toDto(eventoAtualizado));
    }
}