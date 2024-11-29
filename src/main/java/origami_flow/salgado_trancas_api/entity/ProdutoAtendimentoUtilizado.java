package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.*;
import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoAtendimentoUtilizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private FinalidadeProdutoAtendimentoEnum finalidade;

    private Integer quantidade;

    @ManyToOne
    private AtendimentoRealizado atendimentoRealizado;

    @ManyToOne
    private Produto produto;
}
