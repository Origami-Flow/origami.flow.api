package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.estoque.EstoqueDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.mapper.EstoqueMapper;
import origami_flow.salgado_trancas_api.service.EstoqueService;
import origami_flow.salgado_trancas_api.utils.ExportCsv;
import origami_flow.salgado_trancas_api.utils.Ordenacao;

import java.util.List;

@RestController
@RequestMapping("/estoques")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    private final EstoqueMapper estoqueMapper;

    @Operation(summary = "Listar produtos em estoque",
            description = "Retorna uma lista de produtos disponíveis em estoque com detalhes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado no estoque")
    })
    @GetMapping
    public ResponseEntity<List<EstoqueDetalheResponseDTO>> listarProdutosEmEstoque() {
        List<Estoque> produtosEmEstoque = estoqueService.listarProdutosEmEstoque();
        if (produtosEmEstoque.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtosEmEstoque.stream().map(estoqueMapper::toEstoqueDetalheResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDetalheResponseDTO> ProdutoPorId(@PathVariable Integer id) {
        Estoque produto = estoqueService.estoquePorId(id);
        return ResponseEntity.ok(estoqueMapper.toEstoqueDetalheResponseDTO(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstoqueDetalheResponseDTO> atualizarEstoque(@PathVariable Integer id, @RequestParam Integer quantidade) {
        Estoque produto = estoqueService.atualizarEstoque(id, quantidade);
        return ResponseEntity.ok(estoqueMapper.toEstoqueDetalheResponseDTO(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@PathVariable Integer id) {
        estoqueService.removerDoEstoque(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Exportar estoque para CSV",
            description = "Exporta a lista de produtos em estoque para um arquivo CSV ordenado pelo valor de compra.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo CSV exportado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.class)))

    })
    @GetMapping("/csv")
    public void exportarCsvEstoque() {
        List<Estoque> listaOrdenada = estoqueService.ordenar();
        estoqueService.exportar(listaOrdenada);
    }
}
