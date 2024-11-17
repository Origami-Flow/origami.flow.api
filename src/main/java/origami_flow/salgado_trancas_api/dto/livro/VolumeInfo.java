package origami_flow.salgado_trancas_api.dto.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private ImageLinks imageLinks;
    private String previewLink;
}
