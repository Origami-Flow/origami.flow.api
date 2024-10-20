package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping
    public ResponseEntity<List<EstoqueDetalheResponseDTO>> listarProdutosEmEstoque() {
        List<Estoque> produtosEmEstoque = estoqueService.listarProdutosEmEstoque();
        if (produtosEmEstoque.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(produtosEmEstoque.stream().map(estoqueMapper::toEstoqueDetalheResponseDTO).toList());
    }

    @GetMapping("/csv")
    public void exportarCsvEstoque() {
        List<Estoque> lista = estoqueService.listarProdutosEmEstoque();
        System.out.println(lista);
        Ordenacao.quickSortValorCompra(lista, 0, lista.size()-1);
        System.out.println(lista);
        ExportCsv.exportar(lista);
    }
}
