package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.ProdutoRequestDTO;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.mapper.ProdutoMapper;
import origami_flow.salgado_trancas_api.service.ProdutoService;

import java.util.List;

@RequestMapping("/produtos")
@RestController
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    private final ProdutoMapper produtoMapper;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> lista = produtoService.listarTodosProdutos();

        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    //Em revisao
    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody @Valid ProdutoRequestDTO produtoRequestDTO) {
        Produto produtoRetorno = produtoService.cadastrarProduto(produtoMapper.toProdutoEntity(produtoRequestDTO), produtoRequestDTO.getIdSalao(), produtoRequestDTO.getQuantidade());
        return ResponseEntity.created(null).body(produtoRetorno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody @Valid Produto produto, @PathVariable Integer id) {
        Produto produtoRetorno = produtoService.atualizarProduto(id, produto);

        return ResponseEntity.ok(produtoRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filtro-nome")
    public ResponseEntity<Produto> buscarPorNome(@RequestParam String nome) {

        Produto produto = produtoService.buscarProdutoNome(nome);

        return ResponseEntity.ok(produto);
    }
}
