package origami_flow.salgado_trancas_api.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PesquisaBinaria {


    public static Produto buscarProdutoPorNome(List<Produto> produtos, String nome) {
        int inicio = 0;
        int fim = produtos.size() - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            Produto meioProduto = produtos.get(meio);
            int comparacao = meioProduto.getNome().compareToIgnoreCase(nome);

            if (comparacao == 0) {
                return meioProduto;
            } else if (comparacao < 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return null;
    }
}
