package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String descricao;

    @Column(name = "tempo_duracao")
    private LocalTime tempoDuracao;

    @Column(name = "valor_minimo_servico")
    private Double valorMinimoServico;

    @Column(name = "valor_maximo_servico")
    private Double valorMaximoServico;

    @Column(name = "valor_sinal")
    private Double valorSinal;

    @ManyToOne
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;
}
