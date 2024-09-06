package origami_flow.salgado_trancas_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import origami_flow.salgado_trancas_api.dto.livro.Item;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroApiExternoDto {

    @JsonProperty("items")
    private List<Item> items;

//    private String subtitle;
//    private List<String> authors;
//    private String publishDate;
//    private String coverUrl;
//    private List<String> subjects;
//    private String isbn;
}
