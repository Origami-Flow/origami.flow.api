package origami_flow.salgado_trancas_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Avaliacao;
import origami_flow.salgado_trancas_api.entity.Cliente;

import java.util.List;

public interface AvaliacaoClienteRepository extends JpaRepository<Avaliacao,Integer> {

    List<Avaliacao> findAllByCliente(Cliente cliente);

    Boolean existsByAtendimentoRealizado(AtendimentoRealizado atendimentoRealizado);
}
