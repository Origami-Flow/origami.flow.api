package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Lista todos os auxiliares", description = "Retorna uma lista de todos os auxiliares cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum auxiliar encontrado")
    })
    @GetMapping
    public ResponseEntity<List<AuxiliarDetalheResponseDTO>> listarAuxiliar() {
        List<Auxiliar> lista = auxiliarService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(auxiliarMapper::toDto).toList());
    }

    @Operation(summary = "Lista auxiliares por nome", description = "Retorna uma lista de auxiliares que correspondem ao nome informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum auxiliar encontrado")
    })
    @GetMapping("/nome")
    public ResponseEntity<List<AuxiliarDetalheResponseDTO>> listarAuxiliarPorNome(@RequestParam String nome) {
        List<Auxiliar> lista = auxiliarService.listarPorNome(nome);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(auxiliarMapper::toDto).toList());
    }

    @Operation(summary = "Busca auxiliar por ID", description = "Busca um auxiliar específico pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auxiliar encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Auxiliar não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AuxiliarDetalheResponseDTO> buscarAuxiliarPorId(@PathVariable Integer id) {
        Auxiliar auxiliar = auxiliarService.auxiliarPorId(id);
        return ResponseEntity.ok(auxiliarMapper.toDto(auxiliar));
    }

    @Operation(summary = "Cadastra um novo auxiliar", description = "Cria um novo auxiliar com os dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auxiliar cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PostMapping
    public ResponseEntity<AuxiliarDetalheResponseDTO> cadastrarAuxiliar(@RequestBody AuxiliarRequestDTO auxiliarRequestDTO) {
        Auxiliar auxiliar = auxiliarService.cadastrar(auxiliarMapper.toEntity(auxiliarRequestDTO));
        return ResponseEntity.ok(auxiliarMapper.toDto(auxiliar));
    }

    @Operation(summary = "Atualiza um auxiliar", description = "Atualiza os dados de um auxiliar específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auxiliar atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Auxiliar não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AuxiliarDetalheResponseDTO> atualizarAuxiliar(
            @PathVariable Integer id,
            @RequestBody AuxiliarAtualizacaoRequestDTO auxiliarAtualizacaoRequestDTO) {
        Auxiliar auxiliar = auxiliarService.atualizar(id, auxiliarMapper.toEntity(auxiliarAtualizacaoRequestDTO));
        return ResponseEntity.ok(auxiliarMapper.toDto(auxiliar));
    }
}
