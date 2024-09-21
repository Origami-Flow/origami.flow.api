package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.service.ProdutoService;

import java.util.List;

@RequestMapping("/produtos")
@RestController
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodosProdutos() {
        List<Produto> lista = produtoService.listarTodosProdutos();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody @Valid Produto produto) {
        Produto produtoRetorno = produtoService.adicionarProduto(produto);

        return ResponseEntity.created(null).body(produtoRetorno);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody @Valid Produto produto, @PathVariable Integer id) {
        Produto produtoRetorno = produtoService.atualizarProduto(id, produto);

        return ResponseEntity.ok(produtoRetorno);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

}
