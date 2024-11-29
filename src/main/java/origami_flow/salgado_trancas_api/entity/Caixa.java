package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

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

    @Column(name = "data_abertura")
    private LocalDate dataAbertura;
    
    @Column(name = "receita_total")
    private Double receitaTotal;

    @Column(name = "despesa_total")
    private Double despesaTotal;

    @ManyToOne
    private Salao salao;
}