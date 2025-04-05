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
import origami_flow.salgado_trancas_api.dto.request.ProdutoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ProdutoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.estoque.EstoqueDetalheResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.produto.ProdutoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.mapper.ProdutoMapper;
import origami_flow.salgado_trancas_api.service.ProdutoService;

import java.util.List;

@RequestMapping("/produtos")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista de todos os produtos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.ProdutoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ProdutoDetalheResponseDTO>> listarTodosProdutos() {
        List<Produto> lista = produtoService.listarTodosProdutos();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(produtoMapper::toProdutoDetalheResponseDTO).toList());
    }

    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto especifico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDetalheResponseDTO.class))),
            @ApiResponse(responseCode ="404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalheResponseDTO> produtoPorId(@PathVariable Integer id) {
        Produto produto = produtoService.produtoPorId(id);
        return ResponseEntity.ok(produtoMapper.toProdutoDetalheResponseDTO(produto));
    }

    @Operation(summary = "Adicionar um novo produto", description = "Cadastra um novo produto com as informações fornecidas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.ProdutoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "409", description = "Entidade duplicada")
    })
    @PostMapping
    public ResponseEntity<ProdutoDetalheResponseDTO> adicionarProduto(@ModelAttribute @Valid ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoRetorno = produtoService.cadastrarProduto(produtoMapper.toProdutoEntity(produtoRequestDTO), produtoRequestDTO.getIdSalao(), produtoRequestDTO.getQuantidade(), produtoRequestDTO.getImagem());
        return ResponseEntity.created(null).body(produtoMapper.toProdutoDetalheResponseDTO(produtoRetorno));
    }

    @Operation(summary = "Atualizar um produto", description = "Atualiza as informações de um produto específico com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.ProdutoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDetalheResponseDTO> atualizarProduto(@ModelAttribute @Valid ProdutoAtualizacaoRequestDTO produtoAtualizacaoRequestDTO, @PathVariable Integer id) {
        Produto produtoRetorno = produtoService.atualizarProduto(id, produtoMapper.toProdutoEntity(produtoAtualizacaoRequestDTO), produtoAtualizacaoRequestDTO.getImagem());
        return ResponseEntity.ok(produtoMapper.toProdutoDetalheResponseDTO(produtoRetorno));
    }

    @Operation(summary = "Deletar um produto", description = "Remove um produto específico com base no ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar produto por nome", description = "Busca uma lista de produtos com base no nome fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstoqueDetalheResponseDTO.ProdutoDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado por nome especifico")
    })
    @GetMapping("/filtro-nome")
    public ResponseEntity<List<ProdutoDetalheResponseDTO>> buscarPorNome(@RequestParam String nome) {
        List<Produto> produtos = produtoService.buscarProdutoNome(nome);
        if (produtos.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtos.stream().map(produtoMapper::toProdutoDetalheResponseDTO).toList());
    }
}
