package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.response.estoque.EstoqueDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.mapper.EstoqueMapper;
import origami_flow.salgado_trancas_api.service.EstoqueService;
import origami_flow.salgado_trancas_api.utils.ExportCsv;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/estoques")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
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

    @Operation(summary = "Busca produto por ID", description = "Retorna os detalhes do produto específico pelo seu ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDetalheResponseDTO> ProdutoPorId(@PathVariable Integer id) {
        Estoque produto = estoqueService.estoquePorId(id);
        return ResponseEntity.ok(estoqueMapper.toEstoqueDetalheResponseDTO(produto));
    }

    @Operation(summary = "Atualiza um estoque", description = "Retorna os detalhes do produto específico pelo seu ID.")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EstoqueDetalheResponseDTO> atualizarEstoque(@PathVariable Integer id, @RequestParam Integer quantidade) {
        Estoque produto = estoqueService.atualizarEstoque(id, quantidade);
        return ResponseEntity.ok(estoqueMapper.toEstoqueDetalheResponseDTO(produto));
    }
    @Operation(summary = "Remove um estoque", description = "Remove um estoque do sistema com base em seu ID")
    @ApiResponse(responseCode = "204", description = "Estoque deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
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
    @GetMapping(value = "/csv", produces = "text/csv")
    public ResponseEntity exportarCsvEstoque(){
        List<Estoque> listaOrdenada = estoqueService.ordenar();
        estoqueService.exportar(listaOrdenada);
        Resource fileResource = new FileSystemResource(ExportCsv.getCsvPathFile());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"estoque.csv\"")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(fileResource);
    }
}
