package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Agenda;

import java.util.List;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    List<Agenda> findByMes(String mes);

    boolean existsByDiaAndAndMesAndAno(Integer dia, String mes, Integer ano);
}
