package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Evento;

import java.time.LocalTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query("select e from Evento e where e.dataHoraInicio >= :inicio and e.dataHoraTermino <= :fim")
    List<Evento> findAllByHorarioBetween(LocalTime inicio, LocalTime fim);
}
