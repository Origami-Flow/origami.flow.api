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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario extends UsuarioAbstract {

    @PastOrPresent
    @NotNull
    private LocalDate dt_nascimento;
    @Size(min = 11, max = 11)
    @NotBlank
    private String telefone;
    @NotBlank
    private String genero;
    @NotBlank
    private String tipo_cabelo;
    @NotBlank
    private String cor_cabelo;
    @NotBlank
    private String ocupacao;
    @ManyToOne()
    private Endereco endereco;
    @ManyToOne()
    private AvaliacaoUsuario avaliacaoUsuario;
}
