package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    private Double valor;

    private LocalDate data;

    @ManyToOne
    private Caixa caixa;

    @ManyToOne
    private Produto produto;

}
