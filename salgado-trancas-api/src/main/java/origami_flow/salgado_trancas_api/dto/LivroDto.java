package origami_flow.salgado_trancas_api.dto;


import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {
    private String id;
    private String title;
    private String authors;
    private String publishedDate;
    private String smallThumbnail;
    private String thumbnail;
    private String previewLink;
    private Boolean isAvailable;
    private String downloadLink;
}
