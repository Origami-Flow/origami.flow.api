package origami_flow.salgado_trancas_api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "datahora_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraInicio;

    @Column(name = "datahora_termino")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraTermino;

    @Column(name = "tipo_evento")
    @Enumerated(EnumType.STRING)
    private TipoEventoEnum tipoEvento;

    @Enumerated(EnumType.STRING)
    @Column(name= "status_evento")
    private StatusEventoEnum statusEvento;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Servico servico;

    @ManyToOne
    private Trancista trancista;

    @ManyToOne
    private Auxiliar auxiliar;
}
