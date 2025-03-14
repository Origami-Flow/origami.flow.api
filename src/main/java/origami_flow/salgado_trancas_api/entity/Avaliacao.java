package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comentario;

    @NotNull
    private Double nota;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Salao salao;

    @ManyToOne
    private AtendimentoRealizado atendimentoRealizado;
}
