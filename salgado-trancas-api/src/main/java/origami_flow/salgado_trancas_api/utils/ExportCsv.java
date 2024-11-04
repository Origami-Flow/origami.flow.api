package origami_flow.salgado_trancas_api.utils;

import origami_flow.salgado_trancas_api.entity.Estoque;

import java.io.*;
import java.util.List;

public class ExportCsv {

    public static void exportar(List<Estoque> produtoEmEstoque) {
        try(
                OutputStream file = new FileOutputStream("estoque.csv");
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(file));
                ){
            String pattern = "%s;%s;%s;%s;%d;%s;%.2f;%.2f;%s;%d \n";

            for (Estoque estoque : produtoEmEstoque) {
                bufferedWriter.write(pattern.formatted(
                        estoque.getSalao().getNome(),
                        estoque.getSalao().getEndereco().getCep(),
                        estoque.getProduto().getNome(),
                        estoque.getProduto().getMarca(),
                        estoque.getProduto().getQuantidadeEmbalagem(),
                        estoque.getProduto().getUnidadeMedida(),
                        estoque.getProduto().getValorCompra(),
                        estoque.getProduto().getValorVenda(),
                        estoque.getProduto().getFuncionalidade(),
                        estoque.getQuantidade()
                ));
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Erro ao exportar arquivo");
        }

    }
}
