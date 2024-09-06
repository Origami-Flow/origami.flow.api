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
public class Cliente extends Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    private LocalDate dt_nascimento;
    private String telefone;
    private String genero;
    private String tipo_de_cabelo;
    private String ocupacao;
    @ManyToOne()
    private Endereco endereco;
}
