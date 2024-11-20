package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import origami_flow.salgado_trancas_api.entity.Despesa;

import java.math.BigDecimal;

public interface DespesaRepository extends JpaRepository<Despesa,Integer> {

    @Query("SELECT COALESCE(SUM(d.valor), 0) FROM Despesa d WHERE YEAR(d.data) = :ano AND MONTH(d.data) = :mes")
    Double getTotalDespesaMensal(@Param("ano") int ano, @Param("mes") int mes);

}
