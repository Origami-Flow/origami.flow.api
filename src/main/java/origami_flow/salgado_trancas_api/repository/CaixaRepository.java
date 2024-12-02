package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Caixa;

import java.time.LocalDate;
import java.util.Optional;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {

    @Query("select c from Caixa c where" +
            " function('MONTH', c.dataAbertura) = :mes and" +
            " function('YEAR', c.dataAbertura) = :ano")
    Optional<Caixa> buscarCaixaPorMes(int mes, int ano);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Caixa c WHERE " +
            "function('MONTH', c.dataAbertura) = :mes AND " +
            "function('YEAR', c.dataAbertura) = :ano AND " +
            "function('MONTH', c.dataFechamento) = :mes AND " +
            "function('YEAR', c.dataFechamento) = :ano")
    boolean verificarCaixaMes(int mes, int ano);
}
