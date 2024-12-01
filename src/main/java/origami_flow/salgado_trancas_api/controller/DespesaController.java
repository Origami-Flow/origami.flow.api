package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.DespesaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.DespesaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Despesa;
import origami_flow.salgado_trancas_api.mapper.DespesaMapper;
import origami_flow.salgado_trancas_api.service.DespesaService;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaService despesaService;
    private final DespesaMapper despesaMapper;

    @Operation(summary = "Listar despesas", description = "Retorna uma lista de todas as despesas registradas.")
    @ApiResponse(responseCode = "200", description = "Lista de despesas retornada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DespesaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "204", description = "Nenhuma despesa encontrada")
    @GetMapping
    public ResponseEntity<List<DespesaDetalheResponseDTO>> listarDespesas(){
        List<Despesa> despesas = despesaService.listarTodasDepespesas();
        if (despesas.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(despesas.stream().map(despesaMapper::toDespesaDetalheResponseDTO).toList());
    }

    @Operation(summary = "Buscar despesa por ID", description = "Retorna os detalhes de uma despesa com base no ID fornecido.")
    @ApiResponse(responseCode = "200", description = "Despesa encontrada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DespesaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<DespesaDetalheResponseDTO> despesaPorId(@PathVariable Integer id){
        Despesa despesa = despesaService.despesaPorId(id);
        return ResponseEntity.ok().body(despesaMapper.toDespesaDetalheResponseDTO(despesa));
    }

    @Operation(summary = "Criar nova despesa", description = "Cadastra uma nova despesa com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Despesa criada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DespesaDetalheResponseDTO.class)))
    @PostMapping
    public ResponseEntity<DespesaDetalheResponseDTO> criarNovaDespesa(@RequestBody DespesaRequestDTO despesaRequestDTO){
        Despesa despesa = despesaService.cadastrarDespesa(despesaMapper.toDespesaEntity(despesaRequestDTO), despesaRequestDTO.getIdProduto(), despesaRequestDTO.getIdCaixa());
        return ResponseEntity.created(null).body(despesaMapper.toDespesaDetalheResponseDTO(despesa));
    }

    @Operation(summary = "Atualizar despesa", description = "Atualiza os dados de uma despesa existente com base no ID e nos novos dados fornecidos.")
    @ApiResponse(responseCode = "200", description = "Despesa atualizada com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = DespesaDetalheResponseDTO.class)))
    @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<DespesaDetalheResponseDTO> atualizarDespesa(@RequestBody DespesaRequestDTO despesaRequestDTO, @PathVariable Integer id){
        Despesa despesa = despesaService.atualizarDespesa(id, despesaMapper.toDespesaEntity(despesaRequestDTO), despesaRequestDTO.getIdProduto(), despesaRequestDTO.getIdCaixa());
        return ResponseEntity.ok(despesaMapper.toDespesaDetalheResponseDTO(despesa));
    }

    @Operation(summary = "Deletar despesa", description = "Deleta uma despesa com base no ID fornecido.")
    @ApiResponse(responseCode = "204", description = "Despesa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Despesa não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletearDespesa(@PathVariable Integer id){
        despesaService.deletarDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Total de despesas mensais", description = "Retorna o total das despesas em um período específico.")
    @ApiResponse(responseCode = "200", description = "Total de despesas calculado com sucesso",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Double.class)))
    @GetMapping("/despesa/total")
    public ResponseEntity<Double> totalDespesaMensal(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
        return ResponseEntity.ok(despesaService.totalDespesasMensal(inicio, fim));
    }
}
