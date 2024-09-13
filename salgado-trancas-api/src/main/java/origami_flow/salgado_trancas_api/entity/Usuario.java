package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
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
    private LocalDate dt_nascimento;
    private String telefone;
    private String genero;
    private String tipo_cabelo;
    private String cor_cabelo;
    private String ocupacao;
    @ManyToOne()
    private Endereco endereco;
    @ManyToOne()
    private AvaliacaoUsuario avaliacaoUsuario;
}
