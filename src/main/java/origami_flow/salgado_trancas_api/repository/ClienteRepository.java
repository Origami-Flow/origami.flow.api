package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import origami_flow.salgado_trancas_api.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("select c from Cliente c where lower(c.nome) like concat('%',lower(:nome),'%') ")
    List<Cliente> buscarPorNome(String nome);

    boolean existsByTelefoneOrEmail(String telefone, String email);

    UserDetails findByEmail(String email);

    @Query("select c from Cliente c where c.email = :email")
    Optional<Cliente> buscarPorEmail(String email);

    @Query("select count(c) from Cliente c where function('MONTH', c.dataCriacao) = :mes and function('YEAR', c.dataCriacao) = :ano ")
    Integer buscarNovosClientesNoMes(int mes, int ano);
}
