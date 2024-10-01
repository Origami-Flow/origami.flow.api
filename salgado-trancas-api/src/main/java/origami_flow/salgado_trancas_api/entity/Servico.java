package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Time;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_cobrado_cliente")
    private Double valorCobradoCliente;

    @Column(name = "custo_mao_obra")
    private Double custoMaoObra;

    @Column(name = "tempo_duracao")
    private Time tempoDuracao;
}
