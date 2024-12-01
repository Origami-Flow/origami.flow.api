package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.AuxiliarAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.AuxiliarRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.AuxiliarDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Auxiliar;
import origami_flow.salgado_trancas_api.mapper.AuxiliarMapper;
import origami_flow.salgado_trancas_api.service.AuxiliarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auxiliares")
public class AuxiliarController {

    private final AuxiliarService auxiliarService;
    private final AuxiliarMapper auxiliarMapper;

    @Operation(summary = "Listar todos os auxiliares", description = "Retorna a lista de todos os auxiliares cadastrados.")
    @ApiResponse(responseCode = "200", description = "Lista de auxiliares retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuxiliarDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhum auxiliar encontrado")
    @GetMapping
    public ResponseEntity<List<AuxiliarDetalheResponseDTO>> listarAuxiliar() {
        List<Auxiliar> lista = auxiliarService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(auxiliarMapper::toDto).toList());
    }

    @Operation(summary = "Listar auxiliares por nome", description = "Retorna a lista de auxiliares filtrados pelo nome.")
    @ApiResponse(responseCode = "200", description = "Lista de auxiliares filtrada por nome retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuxiliarDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhum auxiliar encontrado com o nome fornecido")
    @GetMapping("/nome")
    public ResponseEntity<List<AuxiliarDetalheResponseDTO>> listarAuxiliarPorNome(@RequestParam String nome) {
        List<Auxiliar> lista = auxiliarService.listarPorNome(nome);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(auxiliarMapper::toDto).toList());
    }

    @Operation(summary = "Buscar auxiliar por ID", description = "Retorna os detalhes de um auxiliar específico com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Auxiliar encontrado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuxiliarDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Auxiliar não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<AuxiliarDetalheResponseDTO> buscarAuxiliarPorId(@PathVariable Integer id) {
        Auxiliar auxiliar = auxiliarService.auxiliarPorId(id);
        return ResponseEntity.ok(auxiliarMapper.toDto(auxiliar));
    }

    @Operation(summary = "Cadastrar um novo auxiliar", description = "Cria um novo auxiliar com base nas informações fornecidas.")
    @ApiResponse(responseCode = "200", description = "Auxiliar cadastrado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuxiliarDetalheResponseDTO.class)))
    @PostMapping
    public ResponseEntity<AuxiliarDetalheResponseDTO> cadastrarAuxiliar(@RequestBody AuxiliarRequestDTO auxiliarRequestDTO) {
        Auxiliar auxiliar = auxiliarService.cadastrar(auxiliarMapper.toEntity(auxiliarRequestDTO));
        return ResponseEntity.ok(auxiliarMapper.toDto(auxiliar));
    }

    @Operation(summary = "Atualizar auxiliar", description = "Atualiza as informações de um auxiliar existente com base no ID e nos dados fornecidos.")
    @ApiResponse(responseCode = "200", description = "Auxiliar atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AuxiliarDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Auxiliar não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<AuxiliarDetalheResponseDTO> atualizarAuxiliar(@PathVariable Integer id, @RequestBody AuxiliarAtualizacaoRequestDTO auxiliarAtualizacaoRequestDTO) {
        Auxiliar auxiliar = auxiliarService.atualizar(id, auxiliarMapper.toEntity(auxiliarAtualizacaoRequestDTO));
        return ResponseEntity.ok(auxiliarMapper.toDto(auxiliar));
    }
}
