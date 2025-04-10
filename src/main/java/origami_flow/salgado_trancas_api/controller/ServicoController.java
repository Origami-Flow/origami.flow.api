package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import origami_flow.salgado_trancas_api.dto.request.ServicoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ServicoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.ServicoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.mapper.ServicoMapper;
import origami_flow.salgado_trancas_api.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    private final ServicoMapper servicoMapper;

    @Operation(summary = "Buscar todos os serviços", description = "Retorna uma lista de todos os serviços cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de serviços retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum serviço encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ServicoDetalheResponseDTO>> buscarServico() {
        List<Servico> lista = servicoService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(servicoMapper::toResponseDTO).toList());
    }

    @Operation(summary = "Buscar serviço por ID", description = "Retorna os detalhes do serviço específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServicoDetalheResponseDTO> buscarPorId(@PathVariable Integer id) {
        Servico servico = servicoService.servicoPorId(id);
        return ResponseEntity.ok(servicoMapper.toResponseDTO(servico));
    }

    @Operation(summary = "Criar um novo serviço", description = "Cria um novo serviço no sistema com base nas informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoDetalheResponseDTO.class)))
    })
    @PostMapping
    public ResponseEntity<ServicoDetalheResponseDTO> criarServico(@ModelAttribute @Valid ServicoRequestDTO servicoRequestDTO) {
        Servico servico = servicoService.criarServico(servicoMapper.toEntity(servicoRequestDTO), servicoRequestDTO.getImagem());
        return ResponseEntity.created(null).body(servicoMapper.toResponseDTO(servico));
    }

    @Operation(summary = "Atualizar serviço", description = "Atualiza as informações de um serviço específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServicoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ServicoDetalheResponseDTO> atualizarServico(@PathVariable Integer id,
          @ModelAttribute ServicoAtualizacaoRequestDTO servicoAtualizacaoRequestDTO) {
        Servico servico = servicoService.atualizarServico(
              servicoMapper.toEntity(servicoAtualizacaoRequestDTO), id,
              servicoAtualizacaoRequestDTO.getImagem());
        return ResponseEntity.ok(servicoMapper.toResponseDTO(servico));
    }

    @Operation(summary = "Excluir serviço", description = "Remove um serviço do sistema com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerServico(@PathVariable Integer id) {
        servicoService.removerServico(id);
        return ResponseEntity.noContent().build();
    }
}
