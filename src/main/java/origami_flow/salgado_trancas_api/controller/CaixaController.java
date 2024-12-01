package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.CaixaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.CaixaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.mapper.CaixaMapper;
import origami_flow.salgado_trancas_api.service.CaixaService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/caixas")
public class CaixaController {

    private final CaixaService caixaService;
    private final CaixaMapper caixaMapper;

    @Operation(summary = "Listar caixas", description = "Retorna uma lista de todos os caixas registrados.")
    @ApiResponse(responseCode = "200", description = "Lista de caixas retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CaixaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhum caixa encontrado")
    @GetMapping
    public ResponseEntity<List<CaixaDetalheResponseDTO>> listarCaixa(){
        List<Caixa> caixas = caixaService.listarTodosCaixas();
        if (caixas.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(caixas.stream().map(caixaMapper::toCaixaDetalheResponseDTO).toList());
    }

    @Operation(summary = "Buscar caixa por ID", description = "Retorna os detalhes de um caixa com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Caixa encontrado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CaixaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Caixa não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CaixaDetalheResponseDTO> caixaPorId(@PathVariable Integer id){
        Caixa caixa = caixaService.caixaPorId(id);
        return ResponseEntity.ok(caixaMapper.toCaixaDetalheResponseDTO(caixa));
    }

    @Operation(summary = "Buscar caixa por mês", description = "Retorna os detalhes do caixa para um mês e ano específicos.")
    @ApiResponse(responseCode = "200", description = "Caixa encontrado para o mês e ano fornecidos",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CaixaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Caixa não encontrado para o mês e ano fornecidos")
    @GetMapping("/por/mes")
    public ResponseEntity<CaixaDetalheResponseDTO> caixaPorMes(@RequestParam int mes, @RequestParam int ano){
        return ResponseEntity.ok().body(caixaMapper.toCaixaDetalheResponseDTO(caixaService.buscarCaixaPorMes(mes, ano)));
    }

    @Operation(summary = "Abrir caixa", description = "Abre um novo caixa para o ID fornecido com o período de início e término.")
    @ApiResponse(responseCode = "201", description = "Caixa aberto com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CaixaDetalheResponseDTO.class)))
    @PostMapping("/{id}")
    public ResponseEntity<CaixaDetalheResponseDTO> cadastrarCaixa(@PathVariable Integer id){
        Caixa caixa = caixaService.abrirCaixa(id);
        return ResponseEntity.created(null).body(caixaMapper.toCaixaDetalheResponseDTO(caixa));

    }

    @Operation(summary = "Atualizar caixa", description = "Atualiza os detalhes de um caixa existente com base no ID e nas informações fornecidas.")
    @ApiResponse(responseCode = "200", description = "Caixa atualizado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CaixaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Caixa não encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<CaixaDetalheResponseDTO> atualizarCaixa(@PathVariable Integer id, @RequestBody @Valid CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = caixaService.atualizarCaixa(id, caixaMapper.toCaixaEntity(caixaRequestDTO), caixaRequestDTO.getSalaoId());
        return ResponseEntity.ok(caixaMapper.toCaixaDetalheResponseDTO(caixa));
    }

    @Operation(summary = "Deletar caixa", description = "Deleta um caixa com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Caixa deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Caixa não encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCaixa(@PathVariable Integer id){
        caixaService.deletarCaixa(id);
        return ResponseEntity.noContent().build();
    }
}
