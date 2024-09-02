package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    Cliente findByEmailAndSenha(String email, String senha);
}
