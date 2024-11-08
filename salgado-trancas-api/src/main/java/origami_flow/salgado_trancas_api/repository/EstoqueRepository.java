package origami_flow.salgado_trancas_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Estoque;

import java.util.Optional;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
        Optional<Estoque> findByProdutoId (Integer id);
}
