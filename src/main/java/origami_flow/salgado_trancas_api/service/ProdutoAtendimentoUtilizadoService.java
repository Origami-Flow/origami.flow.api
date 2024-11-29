package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;
import origami_flow.salgado_trancas_api.repository.ProdutoAtendimentoUtilizadoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoAtendimentoUtilizadoService {

    private final ProdutoAtendimentoUtilizadoRepository produtoAtendimentoUtilizadoRepository;

    public List<ProdutoAtendimentoUtilizado> registrarProdutoUtilizado(List<ProdutoAtendimentoUtilizado> produtos, AtendimentoRealizado atendimento) {
        produtos.forEach(produto -> {produto.setAtendimentoRealizado(atendimento);});
        return produtoAtendimentoUtilizadoRepository.saveAll(produtos);
    }
}
