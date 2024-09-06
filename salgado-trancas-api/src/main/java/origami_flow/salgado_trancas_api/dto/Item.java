package origami_flow.salgado_trancas_api.dto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class VolumeInfo{
    private String title;
    private List<String> authors;
    private String publishedDate;
    private ImageLinks imageLinks;
    private String previewLink;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ImageLinks {
    private String smallThumbnail;
    private String thumbnail;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class AccessInfo{
    private Pdf pdf;
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Pdf{
    private Boolean isAvailable;
    private String downloadLink;
}