package jwt.api.domain.repository;

import jwt.api.domain.Trancista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrancistaRepository extends JpaRepository<Trancista, Integer> {

    Optional<Trancista> findByEmail(String email);
}
