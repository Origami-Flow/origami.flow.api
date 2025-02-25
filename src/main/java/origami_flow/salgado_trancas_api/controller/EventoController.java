package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.EventoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.EventoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ProdutoUtilizadoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.EventoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.mapper.EventoMapper;
import origami_flow.salgado_trancas_api.service.EventoService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class EventoController {

    private final EventoService eventoService;

    private final EventoMapper eventoMapper;

    @Operation(summary = "Listar todos os eventos", description = "Retorna uma lista de todos os eventos cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum evento encontrado")
    })
    @GetMapping
    public ResponseEntity<List<EventoDetalheResponseDTO>> listarEventos() {
        List<Evento> lista = eventoService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(eventoMapper::toDto).toList());
    }

    @Operation(summary = "Listar eventos por data", description = "Retorna uma lista de eventos filtrados por data específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum evento encontrado para a data especificada")
    })
    @GetMapping("/data")
    public ResponseEntity<List<EventoDetalheResponseDTO>> listarEventosPorData(@RequestParam("data") LocalDate data) {
        List<Evento> lista  = eventoService.listarPorData(data);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(eventoMapper::toDto).toList());
    }

    @Operation(summary = "Buscar evento por ID", description = "Retorna os detalhes de um evento específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventoDetalheResponseDTO> buscarEventoPorId(@PathVariable Integer id) {
        Evento evento = eventoService.eventoPorId(id);
        return ResponseEntity.ok(eventoMapper.toDto(evento));
    }

    @Operation(summary = "Criar evento", description = "Cria um novo evento e retorna seus detalhes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class)))
    })
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

    @Operation(summary = "Atualizar evento", description = "Atualiza um evento existente e retorna os detalhes atualizados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class)))
    })
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

    @Operation(summary = "Remover evento", description = "Remove um evento do sistema pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Evento removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Evento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEvento(@PathVariable Integer id) {
        eventoService.apagarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Finalizar evento", description = "Finaliza um evento e registra os produtos utilizados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento finalizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class)))
    })
    @PutMapping("/finalizar/{id}")
    public ResponseEntity<EventoDetalheResponseDTO> finalizarEvento(@PathVariable Integer id, @RequestBody List<ProdutoUtilizadoRequestDTO> produtosUtilizadoRequestDTO , @RequestParam Double valorCobrado){
        Evento eventoAtualizado = eventoService.finalizarEvento(id, produtosUtilizadoRequestDTO, valorCobrado);
        return ResponseEntity.ok(eventoMapper.toDto(eventoAtualizado));
    }

    @Operation(summary = "Buscar eventos por intervalo de tempo", description = "Busca eventos dentro de um intervalo de tempo especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos encontrados dentro do intervalo",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum evento encontrado dentro do intervalo")
    })
    @GetMapping("/buscar-intervalo-tempo")
    public ResponseEntity<List<EventoDetalheResponseDTO>> buscarSemana(@RequestParam LocalDate inicioIntervalo, @RequestParam LocalDate fimIntervalo) {
        List<Evento> eventos = eventoService.buscarPorData(inicioIntervalo, fimIntervalo);
        if (eventos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(eventos.stream().map(eventoMapper::toDto).toList());
    }



    @Operation(summary = "Listar todos os eventos por usuário", description = "Retorna uma lista de todos os eventos de um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de eventos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum evento encontrado")
    })
    @GetMapping("/por/cliente/{id}")
    public ResponseEntity<List<EventoDetalheResponseDTO>> porCliente(@PathVariable Integer id ){
        List<Evento> eventos = eventoService.buscarEventosPorCliente(id);
        if (eventos.isEmpty() ) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(eventos.stream().map(eventoMapper::toDto).toList());
    }
}
