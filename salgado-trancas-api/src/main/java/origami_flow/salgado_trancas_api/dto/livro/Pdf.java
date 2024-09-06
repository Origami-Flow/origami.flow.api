package origami_flow.salgado_trancas_api.dto.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pdf {
    private Boolean isAvailable;
    private String downloadLink;
}
