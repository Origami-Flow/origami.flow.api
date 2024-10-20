package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import origami_flow.salgado_trancas_api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByEmailAndSenha(String email, String senha);

    boolean existsByTelefoneOrEmail(String telefone, String email);
}
