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


@Entity
@SuperBuilder
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public TipoCabeloEnum getTipoCabelo() {
        return tipoCabelo;
    }

    public void setTipoCabelo(TipoCabeloEnum tipoCabelo) {
        this.tipoCabelo = tipoCabelo;
    }

    public ComprimentoCabeloEnum getComprimentoCabelo() {
        return comprimentoCabelo;
    }

    public void setComprimentoCabelo(ComprimentoCabeloEnum comprimentoCabelo) {
        this.comprimentoCabelo = comprimentoCabelo;
    }

    public String getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(String corCabelo) {
        this.corCabelo = corCabelo;
    }

    public boolean isProgressiva() {
        return progressiva;
    }

    public void setProgressiva(boolean progressiva) {
        this.progressiva = progressiva;
    }

    public boolean isPrimeiraTranca() {
        return primeiraTranca;
    }

    public void setPrimeiraTranca(boolean primeiraTranca) {
        this.primeiraTranca = primeiraTranca;
    }

    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
