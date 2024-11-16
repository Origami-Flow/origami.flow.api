package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtendimentoRealizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double receita;

    @ManyToOne
    private Evento evento;

/*    @ManyToOne
    private Caixa caixa;*/
}
