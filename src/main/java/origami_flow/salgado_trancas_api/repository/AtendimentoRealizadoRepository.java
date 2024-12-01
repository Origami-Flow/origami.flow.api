package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;

import java.time.LocalDateTime;
import java.util.List;

public interface AtendimentoRealizadoRepository extends JpaRepository<AtendimentoRealizado, Integer> {

    @Query("select count(a) from AtendimentoRealizado a where" +
            " function('MONTH', a.evento.dataHoraInicio) = :mes and" +
            " function('MONTH', a.evento.dataHoraTermino) = :mes and" +
            " function('YEAR', a.evento.dataHoraInicio) = :ano and" +
            " function('YEAR', a.evento.dataHoraTermino) = :ano")
    Integer buscarTotalAtendimentoRealizadosNoMes(int mes, int ano);

    @Query("select count(distinct a.evento.cliente.id) from AtendimentoRealizado a where" +
            " function('MONTH', a.evento.cliente.dataCriacao) = :mes and" +
            " function('YEAR', a.evento.cliente.dataCriacao) = :ano and" +
            " function('MONTH', a.evento.dataHoraInicio) = :mes and" +
            " function('MONTH', a.evento.dataHoraTermino) = :mes and" +
            " function('YEAR', a.evento.dataHoraInicio) = :ano and" +
            " function('YEAR', a.evento.dataHoraTermino) = :ano")
    Integer buscarTotalAtendimentoRealizadosNoMesComClientesNovos(int mes, int ano);

    @Query("select a.evento.servico.nome from AtendimentoRealizado a where" +
            " function('MONTH', a.evento.dataHoraInicio) = :mes and" +
            " function('MONTH', a.evento.dataHoraTermino) = :mes and" +
            " function('YEAR', a.evento.dataHoraInicio) = :ano and" +
            " function('YEAR', a.evento.dataHoraTermino) = :ano" +
            " group by a.evento.servico.nome" +
            " order by count(a.evento.servico.nome) desc limit 1")
    String buscarTrancaMaisRealizadaNoMes(int mes, int ano);

    @Query("SELECT a FROM AtendimentoRealizado a " +
            "JOIN a.evento e " +
            "WHERE FUNCTION('MONTH', e.dataHoraInicio) = :mes " +
            "AND FUNCTION('YEAR', e.dataHoraInicio) = :ano")
    List<AtendimentoRealizado> atendimentosPorMes(@Param("mes") int mes, @Param("ano") int ano);

}
