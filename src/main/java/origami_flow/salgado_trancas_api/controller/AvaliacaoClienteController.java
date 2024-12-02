package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.AvaliacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.AvaliacaoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Avaliacao;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.mapper.AvaliacaoMapper;
import origami_flow.salgado_trancas_api.service.AvaliacaoClienteService;

import java.util.List;

@RestController
@RequestMapping("/avaliacao-usuarios")
@RequiredArgsConstructor
public class AvaliacaoClienteController {

    private final AvaliacaoClienteService avaliacaoClienteService;
    private final AvaliacaoMapper avaliacaoMapper;

    @Operation(summary = "Listar avaliações dos usuários", description = "Retorna a lista de todas as avaliações realizadas pelos usuários.")
    @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AvaliacaoDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhuma avaliação encontrada")
    @GetMapping
    public ResponseEntity<List<AvaliacaoDetalheResponseDTO>> listarAvaliacao(){
        List<Avaliacao> lista = avaliacaoClienteService.listarAvaliacao();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(lista.stream().map(avaliacaoMapper::toAvaliacaoDetalheResponseDTO).toList());
    }

    @Operation(summary = "Buscar avaliação por ID", description = "Retorna a avaliação de um usuário específico com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Avaliação encontrada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AvaliacaoDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDetalheResponseDTO> avaliacaoPorId(@PathVariable Integer id){
        Avaliacao avaliacao = avaliacaoClienteService.avaliacaoPorId(id);

        return ResponseEntity.ok(avaliacaoMapper.toAvaliacaoDetalheResponseDTO(avaliacao));
    }

    @Operation(summary = "Criar avaliação", description = "Cria uma nova avaliação de um usuário com base nos dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Avaliação criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AvaliacaoDetalheResponseDTO.class)))
    @PostMapping
    public ResponseEntity<AvaliacaoDetalheResponseDTO> criarAvaliacao(@RequestBody @Valid AvaliacaoRequestDTO avaliacao){
        Avaliacao avaliacaoSalvar = avaliacaoClienteService.criarAvaliacao(avaliacaoMapper.toEntity(avaliacao), avaliacao.getAtendimentoRealizadoId(),avaliacao.getClienteId(),avaliacao.getSalaoId());

        return ResponseEntity.created(null).body(avaliacaoMapper.toAvaliacaoDetalheResponseDTO(avaliacaoSalvar));
    }

    @Operation(summary = "Atualizar avaliação", description = "Atualiza os dados de uma avaliação existente com base no ID e nas informações fornecidas.")
    @ApiResponse(responseCode = "200", description = "Avaliação atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AvaliacaoDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDetalheResponseDTO> atualizarAvaliacao(@PathVariable @Valid Integer id, @RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO){
        Avaliacao avaliacao = avaliacaoClienteService.atualizarAvaliacao(id, avaliacaoMapper.toEntity(avaliacaoRequestDTO),avaliacaoRequestDTO.getAtendimentoRealizadoId(),avaliacaoRequestDTO.getClienteId(),avaliacaoRequestDTO.getSalaoId());
        return ResponseEntity.ok(avaliacaoMapper.toAvaliacaoDetalheResponseDTO(avaliacao));
    }

    @Operation(summary = "Deletar avaliação", description = "Deleta uma avaliação existente com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Avaliação deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Avaliação não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Integer id){
        avaliacaoClienteService.deletarAvaliacao(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar avaliações do usuário especifico", description = "Retorna a lista de todas as avaliações de uum usuário")
    @ApiResponse(responseCode = "200", description = "Lista de avaliações retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AvaliacaoDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhuma avaliação encontrada")
    @GetMapping("/por/cliente/{id}")
    public ResponseEntity<List<AvaliacaoDetalheResponseDTO>> porCliente(@RequestParam Integer id){
        List<Avaliacao> avaliacaos = avaliacaoClienteService.avaliacoesPorCliente(id);
        if (avaliacaos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(avaliacaos.stream().map(avaliacaoMapper::toAvaliacaoDetalheResponseDTO).toList());
    }
}
