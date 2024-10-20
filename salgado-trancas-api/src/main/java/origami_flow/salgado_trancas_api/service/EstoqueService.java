package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.repository.EstoqueRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public void cadastraProdutoNoEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    public List<Estoque> listarProdutosEmEstoque() {
        return estoqueRepository.findAll();
    }
}
