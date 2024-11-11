package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.AgendaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.AgendaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Agenda;
import origami_flow.salgado_trancas_api.mapper.AgendaMapper;
import origami_flow.salgado_trancas_api.service.AgendaService;

import java.util.List;

@RestController("/agendas")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService agendaService;

    private final AgendaMapper agendaMapper;

    @GetMapping()
    public ResponseEntity<List<AgendaDetalheResponseDTO>> listarDatasAgendadas() {
        List<Agenda> lista = agendaService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(agendaMapper::toDetalheResponseDTO).toList());
    }

    @GetMapping("/mes")
    public ResponseEntity<List<AgendaDetalheResponseDTO>> listarDatasDoMes(@RequestParam String mes) {
        List<Agenda> lista = agendaService.listarPorMes(mes);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(agendaMapper::toDetalheResponseDTO).toList());
    }

    @PostMapping
    public ResponseEntity<AgendaDetalheResponseDTO> criarDataAgenda(@RequestBody AgendaRequestDTO agendaRequestDTO) {
        Agenda novaData = agendaService.criarDataAgenda(agendaMapper.toEntity(agendaRequestDTO), agendaRequestDTO.getTrancistaId());
        return ResponseEntity.created(null).body(agendaMapper.toDetalheResponseDTO(novaData));
    }
}
