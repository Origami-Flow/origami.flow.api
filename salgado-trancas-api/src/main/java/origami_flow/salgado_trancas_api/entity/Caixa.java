package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_fechamento")
    private LocalDate dataFechamento;

    private Double lucro;

    @ManyToOne
    @JoinColumn(name = "salao_id")
    private Salao salao;
}