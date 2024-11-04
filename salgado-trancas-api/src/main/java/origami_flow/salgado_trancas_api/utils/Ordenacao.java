package origami_flow.salgado_trancas_api.utils;

import origami_flow.salgado_trancas_api.entity.Estoque;

import java.util.List;

public class Ordenacao {
    public static void quickSortValorCompra(List<Estoque> lista, int indInicio, int indFim) {
        int i = indInicio;
        int j = indFim;
        Double pivo = lista.get((indInicio + indFim) / 2).getProduto().getValorCompra();

        while (i <= j) {

            while (i < indFim && lista.get(i).getProduto().getValorCompra() < pivo) {
                i = i + 1;
            }

            while (j > indInicio && lista.get(j).getProduto().getValorCompra() > pivo) {
                j = j - 1;
            }

            if (i <= j) {
                Estoque aux = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, aux);
                i = i + 1;
                j = j - 1;
            }
        }

        if (indInicio < j) {
            quickSortValorCompra(lista,indInicio, j);
        }
        if (i < indFim) {
            quickSortValorCompra(lista, i, indFim);
        }
    }
}
