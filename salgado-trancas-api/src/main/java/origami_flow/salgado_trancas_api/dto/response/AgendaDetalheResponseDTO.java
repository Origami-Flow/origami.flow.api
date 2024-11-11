package origami_flow.salgado_trancas_api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaDetalheResponseDTO {

    private Integer id;

    private Integer dia;

    private String mes;

    private Integer ano;

    private trancistaDetalheResponseDTO trancistaDetalheResponseDTO;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static  class trancistaDetalheResponseDTO {

        private Integer id;

        private String nome;

        private String email;

        private String tipo;
    }
}
