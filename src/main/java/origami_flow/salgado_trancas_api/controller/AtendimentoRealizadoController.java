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
import origami_flow.salgado_trancas_api.dto.response.AtendimentoRealizadoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.mapper.AtendimentoRealizadoMapper;
import origami_flow.salgado_trancas_api.service.AtendimentoRealizadoService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/atendimento-realizado")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AtendimentoRealizadoController {

    private final AtendimentoRealizadoService atendimentoRealizadoService;
    private final AtendimentoRealizadoMapper atendimentoRealizadoMapper;

    @Operation(
            summary = "Lista todos os atendimentos realizados",
            description = "Endpoint para listar todos os atendimentos realizados.",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de atendimentos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtendimentoRealizadoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum atendimento encontrado")
    })
    @GetMapping
    public ResponseEntity<List<AtendimentoRealizadoDetalheResponseDTO>> listarAtendimentosRealizados() {
        List<AtendimentoRealizado> lista = atendimentoRealizadoService.listarAtendimentosRealizados();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(atendimentoRealizadoMapper::toDto).toList());
    }

    @Operation(
            summary = "Busca atendimento realizado por ID",
            description = "Endpoint para buscar um atendimento realizado específico pelo seu ID.",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimento encontrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtendimentoRealizadoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<AtendimentoRealizadoDetalheResponseDTO> atendimentoRealizadoPorId(@PathVariable Integer id) {
        AtendimentoRealizado atendimentoRealizado = atendimentoRealizadoService.atendimentoRealizadoPorId(id);
        return ResponseEntity.ok(atendimentoRealizadoMapper.toDto(atendimentoRealizado));
    }

    @Operation(
            summary = "Atualiza um atendimento realizado",
            description = "Endpoint para atualizar um atendimento realizado pelo ID.",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Atendimento atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtendimentoRealizadoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos dados enviados")
    })
    @PutMapping("{id}")
    public ResponseEntity<AtendimentoRealizadoDetalheResponseDTO> atualizarAtendimentoRealizado(
            @PathVariable Integer id,
            @RequestBody AtendimentoRealizado atendimentoRealizado) {
        AtendimentoRealizado atendimentoRealizadoRetorno = atendimentoRealizadoService.atualizarAtendimento(id, atendimentoRealizado);
        return ResponseEntity.ok(atendimentoRealizadoMapper.toDto(atendimentoRealizadoRetorno));
    }

    @Operation(
            summary = "Deleta um atendimento realizado",
            description = "Endpoint para deletar um atendimento realizado pelo ID.",
            security = {@SecurityRequirement(name = "bearerAuth")}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Atendimento deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Atendimento não encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAtendimentoRealizado(@PathVariable Integer id) {
        atendimentoRealizadoService.apagarAtendimentoRealizado(id);
        return ResponseEntity.noContent().build();
    }
}
