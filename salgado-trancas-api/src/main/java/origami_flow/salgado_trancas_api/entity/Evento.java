package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "datahora_inicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "datahora_termino")
    private LocalDateTime dataHoraTermino;

    @Enumerated(EnumType.STRING)
    private TipoEventoEnum tipoEvento;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Trancista trancista;

    @ManyToOne
    private Auxiliar auxiliar;
}
