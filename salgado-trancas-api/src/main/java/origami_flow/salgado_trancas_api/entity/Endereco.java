package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cep;

    private String estado;

    private String uf;

    private String regiao;

    private String cidade;

    private String bairro;

    private String logradouro;

    private Integer numero;

    private String complemento;
}
