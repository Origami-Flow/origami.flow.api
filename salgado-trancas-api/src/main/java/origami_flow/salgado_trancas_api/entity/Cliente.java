package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente extends UsuarioAbstract {

    @NotNull
    @PastOrPresent
    @Column(name = "dtNasc")
    private LocalDate dtNascimento;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    @NotBlank
    private String genero;

    @NotBlank
    @Column(name = "tipo_cabelo")
    private String tipoCabelo;

    @NotBlank
    @Column(name = "cor_cabelo")
    private String corCabelo;

    private boolean progressiva;

    private  boolean primeiraTranca;

    @NotBlank
    private String ocupacao;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany
    private List<Atendimento> atendimento;


    @OneToMany
    private List<AvaliacaoCliente> avaliacoes;
}
