package origami_flow.salgado_trancas_api.dto.livro;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private String id;
    private VolumeInfo volumeInfo;
    private AccessInfo accessInfo;

}

