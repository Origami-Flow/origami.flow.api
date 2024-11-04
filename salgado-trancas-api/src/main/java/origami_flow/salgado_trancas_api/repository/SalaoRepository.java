package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Salao;

public interface SalaoRepository extends JpaRepository<Salao, Integer> {
    boolean existsByNome(String nome);
}
