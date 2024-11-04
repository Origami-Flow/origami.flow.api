package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Trancista;

public interface TrancistaRepository extends JpaRepository<Trancista,Integer> {
    Trancista findByEmailAndSenha(String email, String senha);

    boolean existsByEmail(String email);
}
