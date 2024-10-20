package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import origami_flow.salgado_trancas_api.constans.ComprimentoCabeloEnum;
import origami_flow.salgado_trancas_api.constans.GeneroEnum;
import origami_flow.salgado_trancas_api.constans.TipoCabeloEnum;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Cliente extends UsuarioAbstract {

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String telefone;

    private GeneroEnum genero;

    @Column(name = "tipo_cabelo")
    private TipoCabeloEnum tipoCabelo;

    @Column(name = "comprimento_cabelo")
    private ComprimentoCabeloEnum comprimentoCabelo;

    @Column(name = "cor_cabelo")
    private String corCabelo;

    private boolean progressiva;

    @Column(name = "primeira_tranca")
    private  boolean primeiraTranca;

    private String ocupacao;

    @OneToOne
    private Endereco endereco;
}
