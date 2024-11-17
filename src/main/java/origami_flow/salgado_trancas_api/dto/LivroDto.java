package origami_flow.salgado_trancas_api.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroDto {

    @Schema(description = "Identificador único do livro", example = "12345")
    private String id;

    @Schema(description = "Título do livro", example = "O Senhor dos Anéis")
    private String title;

    @Schema(description = "Autores do livro", example = "J.R.R. Tolkien")
    private String authors;

    @Schema(description = "Data de publicação do livro", example = "1954-07-29")
    private String publishedDate;

    @Schema(description = "Link para a miniatura do livro", example = "http://example.com/exemplo.jpg")
    private String smallThumbnail;

    @Schema(description = "Link para a thumbnail do livro", example = "http://example.com/thumbnail.jpg")
    private String thumbnail;

    @Schema(description = "Link para a prévia do livro", example = "http://example.com/preview")
    private String previewLink;

    @Schema(description = "Indica se o livro está disponível", example = "true")
    private Boolean isAvailable;

    @Schema(description = "Link para download do livro", example = "http://example.com/download")
    private String downloadLink;
}
