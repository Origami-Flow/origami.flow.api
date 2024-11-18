package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.SalaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.SalaoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.estoque.EstoqueDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.mapper.SalaoMapper;
import origami_flow.salgado_trancas_api.service.SalaoService;

import java.util.List;

@RestController
@RequestMapping("/saloes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class SalaoController {

    private final SalaoService salaoService;

    private final SalaoMapper salaoMapper;

    @Operation(summary = "Cadastrar um novo salão", description = "Cadastra um salão com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Salão criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Entidade duplicada")
    })
    @PostMapping
    public ResponseEntity<SalaoDetalheResponseDTO> cadastrarSalao(@RequestBody @Valid SalaoRequestDTO salaoRequestDTO) {
        Salao salao = salaoService.cadastrarSalao(salaoMapper.toSalaoEntity(salaoRequestDTO), salaoRequestDTO.getCep());
        return ResponseEntity.created(null).body(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @Operation(summary = "Listar todos os salões", description = "Retorna uma lista de todos os salões cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de salões retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum salão encontrado")
    })
    @GetMapping
    public ResponseEntity<List<SalaoDetalheResponseDTO>> listarSaloes() {
        List<Salao> saloes = salaoService.listar();
        if (saloes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(saloes.stream().map(salaoMapper::toSalaoDetalheResponseDTO).toList());
    }

    @Operation(summary = "Buscar salão por ID", description = "Retorna um salão específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salão encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Salão não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SalaoDetalheResponseDTO> buscarSalao(@PathVariable Integer id) {
        Salao salao = salaoService.salaoPorId(id);
        return ResponseEntity.ok(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @Operation(summary = "Atualizar salão", description = "Atualiza as informações de um salão específico com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Salão atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Salão não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SalaoDetalheResponseDTO> atualizarSalao(@PathVariable Integer id, @RequestBody @Valid SalaoRequestDTO salaoRequestDTO) {
        Salao salao = salaoService.atualizar(id, salaoMapper.toSalaoEntity(salaoRequestDTO));
        return ResponseEntity.ok(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @Operation(summary = "Atualizar endereço do salão", description = "Atualiza o endereço de um salão específico com base no ID e no CEP fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço do salão atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalaoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Salão não encontrado")
    })
    @PutMapping("/{id}/endereco")
    public ResponseEntity<SalaoDetalheResponseDTO> atualizarEnderecoSalao(@PathVariable Integer id, @RequestParam String cep) {
        Salao salao = salaoService.atualizarEndereco(id, cep);
        return ResponseEntity.ok(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirSalao(@PathVariable Integer id) {
        salaoService.deletarSalao(id);
        return ResponseEntity.noContent().build();
    }
}
