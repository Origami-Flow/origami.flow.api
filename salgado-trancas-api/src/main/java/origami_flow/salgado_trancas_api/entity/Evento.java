package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
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

    @NotNull
    @Column(name = "datahora_inicio")
    private LocalDateTime dataHoraInicio;

    @NotNull
    @Column(name = "datahora_termino")
    private LocalDateTime dataHoraTermino;

    private String tipoEvento;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Agenda agenda;
}
