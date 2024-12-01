package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;
import origami_flow.salgado_trancas_api.entity.ProdutoAtendimentoUtilizado;

import java.util.List;

public interface ProdutoAtendimentoUtilizadoRepository extends JpaRepository<ProdutoAtendimentoUtilizado, Integer> {

    @Query("select p from ProdutoAtendimentoUtilizado p where p.finalidade = :finalidade and " +
            "function('MONTH', p.atendimentoRealizado.evento.dataHoraInicio) = :mes and" +
            " function('MONTH', p.atendimentoRealizado.evento.dataHoraTermino) = :mes and " +
            "function('YEAR', p.atendimentoRealizado.evento.dataHoraInicio) = :ano and" +
            " function('YEAR', p.atendimentoRealizado.evento.dataHoraTermino) = :ano")
    List<ProdutoAtendimentoUtilizado> buscarTotalVendasNoMes(FinalidadeProdutoAtendimentoEnum finalidade, int mes, int ano);
}
