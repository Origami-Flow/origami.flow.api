package origami_flow.salgado_trancas_api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class LivroApiExternoDto {


    private String title;
    private String subtitle;
    private List<String> authors;
    private String publishDate;
    private String coverUrl;
    private List<String> subjects;
    private String isbn;


}
