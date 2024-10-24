package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,Integer> {

    boolean existsByNome(String nome);

    List<Produto> findAllByOrderByNome();
}
