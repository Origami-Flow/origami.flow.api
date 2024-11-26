package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import origami_flow.salgado_trancas_api.dto.response.DespesaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Despesa;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface DespesaRepository extends JpaRepository<Despesa,Integer> {

    @Query("SELECT COALESCE(SUM(d.valor), 0) FROM Despesa d WHERE d.data BETWEEN :inicio AND :fim")
    Double getTotalDespesaMensal(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);



}
