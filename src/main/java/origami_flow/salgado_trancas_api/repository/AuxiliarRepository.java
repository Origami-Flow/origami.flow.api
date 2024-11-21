package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Auxiliar;

import java.util.List;

public interface AuxiliarRepository extends JpaRepository<Auxiliar, Integer> {
    @Query("select a from Auxiliar a  where lower(a.nome) like lower(:nome)")
    List<Auxiliar> buscarPorNome(String nome);
}
