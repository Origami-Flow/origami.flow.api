package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Cliente;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where lower(c.nome) like lower(:nome) ")
    List<Cliente> buscarPorNome(String nome);

    boolean existsByTelefoneOrEmail(String telefone, String email);
}
