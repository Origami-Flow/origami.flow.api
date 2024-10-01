package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import origami_flow.salgado_trancas_api.constans.ComprimentoCabeloEnum;
import origami_flow.salgado_trancas_api.constans.GeneroEnum;
import origami_flow.salgado_trancas_api.constans.TipoCabeloEnum;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Cliente extends UsuarioAbstract {

    @Column(name = "dtNasc")
    private LocalDate dtNascimento;

    private String telefone;

    private GeneroEnum genero;

    @Column(name = "tipo_cabelo")
    private TipoCabeloEnum tipoCabelo;

    private ComprimentoCabeloEnum comprimentoCabelo;

    @Column(name = "cor_cabelo")
    private String corCabelo;

    private boolean progressiva;

    private  boolean primeiraTranca;

    private String ocupacao;

//    @ManyToOne
//    @JoinColumn(name = "endereco_id")
//    private Endereco endereco;

//    @OneToMany
//    private List<Atendimento> atendimento;
//
//
//    @OneToMany
//    private List<AvaliacaoCliente> avaliacoes;
}
