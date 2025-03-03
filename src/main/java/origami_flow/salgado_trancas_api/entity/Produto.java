package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.*;
import origami_flow.salgado_trancas_api.constans.TipoProdutoEnum;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private String marca;

    @Column(name = "valor_compra")
    private Double valorCompra;

    @Column(name = "valor_venda")
    private Double valorVenda;

    @Column(name = "quantidade_embalagem")
    private Integer quantidadeEmbalagem;

    @Column(name = "unidade_medida")
    private String unidadeMedida;

    @Enumerated(EnumType.STRING)
    private TipoProdutoEnum tipo;

    @ManyToOne
    @JoinColumn(name = "imagem_id")
    private Imagem imagem;
}
