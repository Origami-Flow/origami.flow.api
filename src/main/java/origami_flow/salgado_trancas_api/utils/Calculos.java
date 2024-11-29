package origami_flow.salgado_trancas_api.utils;

import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;
import origami_flow.salgado_trancas_api.entity.Servico;

import java.util.List;

public class Calculos{

    public static Double calcularReceita(Evento evento , List<ProdutoAtendimentoUtilizado> produtos){
        Double total = 0.0;
        if (!produtos.isEmpty()){
            for (ProdutoAtendimentoUtilizado produto : produtos){
                if (produto.getFinalidade().equals(FinalidadeProdutoAtendimentoEnum.VENDIDO)){
                    total+= produto.getProduto().getValorVenda() * produto.getQuantidade();
                }
            }
        }
        total += evento.getServico().getValorServico() + evento.getServico().getValorSinal();
        return  total;
    }


}
