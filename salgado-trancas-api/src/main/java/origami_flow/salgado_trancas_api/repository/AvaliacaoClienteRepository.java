package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Avaliacao;

public interface AvaliacaoClienteRepository extends JpaRepository<Avaliacao,Integer> {
}
