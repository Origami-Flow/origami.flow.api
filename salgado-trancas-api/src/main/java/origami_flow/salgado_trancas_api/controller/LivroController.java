package origami_flow.salgado_trancas_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import origami_flow.salgado_trancas_api.dto.Item;
import origami_flow.salgado_trancas_api.dto.LivroApiExternoDto;
import origami_flow.salgado_trancas_api.dto.LivroDto;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private static final Logger log = LoggerFactory.getLogger(LivroController.class);

    @GetMapping
    public ResponseEntity<List<Item>> listLivros(
            @RequestParam String title
    ) {
        RestClient client = RestClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1/volumes?q="+ title +"&filter=free-ebooks&langRestrict=pt-BR")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String raw = client.get()
                .uri("?title=" + title)
                .retrieve()
                .body(String.class);

        log.info("Resposta da API: " + raw);

        LivroApiExternoDto result = client.get()
                .uri("?title=" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if(result == null ){
            return ResponseEntity.noContent().build();
        }

//        List<Item> items = result.getItems();
        List<Item> response = result.getItems();
//        List<LivroDto> response = items.stream()
//                .map(item -> LivroDto.builder()
//                        .id(item.getId())
//                        .title(item.getVolumeInfo() != null ? item.getVolumeInfo().getTitle() : null)
//                        .authors(item.getVolumeInfo() != null && item.getVolumeInfo().getAuthors() != null
//                                ? String.join(", ", item.getVolumeInfo().getAuthors()) : null)
//                        .publishedDate(item.getVolumeInfo() != null ? item.getVolumeInfo().getPublishedDate() : null)
//                        .smallThumbnail(item.getVolumeInfo() != null && item.getVolumeInfo().getImageLinks() != null
//                                ? item.getVolumeInfo().getImageLinks().getSmallThumbnail() : null)
//                        .thumbnail(item.getVolumeInfo() != null && item.getVolumeInfo().getImageLinks() != null
//                                ? item.getVolumeInfo().getImageLinks().getThumbnail() : null)
//                        .previewLink(item.getVolumeInfo() != null ? item.getVolumeInfo().getPreviewLink() : null)
//                        .isAvailable(item.getAccessInfo() != null && item.getAccessInfo().getPdf() != null
//                                ? item.getAccessInfo().getPdf().getIsAvailable() : null)
//                        .downloadLink(item.getAccessInfo() != null && item.getAccessInfo().getPdf() != null
//                                ? item.getAccessInfo().getPdf().getDownloadLink() : null)
//                        .build()
//                )
//                .collect(Collectors.toList());


//        private String id;
//        private String title;
//        private String authors;
//        private String publishedDate;
//        private String smallThumbnail;
//        private String thumbnail;
//        private String previewLink;
//        private Boolean isAvailable;
//        private String downloadLink;
        return ResponseEntity.ok(response);

    }

}
