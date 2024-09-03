package origami_flow.salgado_trancas_api.controller;

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
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(lista);
    }

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto) {
        Produto produtoRetorno = produtoService.adicionarProduto(produto);

        return ResponseEntity.status(201).body(produtoRetorno);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto, @PathVariable Integer id) {
        Produto produtoRetorno = produtoService.atualizarProduto(id, produto);
        if (produtoRetorno == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(produtoRetorno);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        if (!produtoService.deletarProduto(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).build();
    }

}
