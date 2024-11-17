package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    boolean existsByCep(String cep);
}
