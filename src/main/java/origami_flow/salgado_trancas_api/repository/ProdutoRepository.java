package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Produto;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    boolean existsByNome(String nome);


    @Query("select p from Produto p  where lower(p.nome) like concat('%',lower(:nome),'%')")
    List<Produto> findAllByOrderByNome(String nome);

}
