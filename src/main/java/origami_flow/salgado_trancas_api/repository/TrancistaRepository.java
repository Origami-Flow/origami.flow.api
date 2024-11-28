package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import origami_flow.salgado_trancas_api.entity.Trancista;

import java.util.Optional;

public interface TrancistaRepository extends JpaRepository<Trancista,Integer> {

    boolean existsByEmailOrTelefone(String email, String telefone);

    Trancista findByEmailAndSenha(String email, String senha);

    UserDetails findByEmail(String email);

    @Query("select t from Trancista t where t.email = :email")
    Optional<Trancista> buscarPorEmail(String email);
}
