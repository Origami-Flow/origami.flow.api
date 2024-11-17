package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    boolean existsByNome(String nome);
}
