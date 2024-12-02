package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Listar atendimentos realizados", description = "Retorna a lista de atendimentos realizados.")
    @ApiResponse(responseCode = "200", description = "Lista de atendimentos realizados retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AtendimentoRealizadoDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhum atendimento realizado encontrado")
    @GetMapping
    public ResponseEntity<List<AtendimentoRealizadoDetalheResponseDTO>> listarAtendimentosRealizados(){
        List<AtendimentoRealizado> lista = atendimentoRealizadoService.listarAtendimentosRealizados();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(atendimentoRealizadoMapper::toDto).toList());
    }

    @Operation(summary = "Obter atendimento realizado por ID", description = "Retorna um atendimento realizado específico com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Atendimento realizado encontrado",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AtendimentoRealizadoDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Atendimento realizado não encontrado")
    @GetMapping("{id}")
    public ResponseEntity<AtendimentoRealizadoDetalheResponseDTO> atendimentoRealizadoPorId(@PathVariable Integer id){
        AtendimentoRealizado atendimentoRealizado = atendimentoRealizadoService.atendimentoRealizadoPorId(id);
        return ResponseEntity.ok(atendimentoRealizadoMapper.toDto(atendimentoRealizado));
    }

    @Operation(summary = "Deletar atendimento realizado", description = "Remove um atendimento realizado com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Atendimento realizado deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Atendimento realizado não encontrado")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAtendimentoRealizado(@PathVariable Integer id){
        atendimentoRealizadoService.apagarAtendimentoRealizado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("por/cliente/{idCliente}")
    public ResponseEntity<List<AtendimentoRealizadoDetalheResponseDTO>> porCliente(@PathVariable Integer idCliente){
        List<AtendimentoRealizado> atendimentoRealizados = atendimentoRealizadoService.atendimentoPorCliente(idCliente);
        if (atendimentoRealizados.isEmpty()) return  ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(atendimentoRealizados.stream().map(atendimentoRealizadoMapper::toDto).toList());
    }
}

