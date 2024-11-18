package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Evento;

import java.time.LocalDate;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    @Query("select e from Evento e where DATE(e.dataHoraInicio) = :data or DATE(e.dataHoraTermino) = :data")
    List<Evento> findByData(LocalDate data);
}