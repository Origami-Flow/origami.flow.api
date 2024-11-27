package origami_flow.salgado_trancas_api.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import origami_flow.salgado_trancas_api.entity.Caixa;

public interface CaixaRepository extends JpaRepository<Caixa, Integer> {

}
