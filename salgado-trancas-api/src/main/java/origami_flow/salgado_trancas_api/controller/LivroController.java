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
import origami_flow.salgado_trancas_api.dto.LivroApiExternoDto;
import origami_flow.salgado_trancas_api.dto.LivroDto;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private static final Logger log = LoggerFactory.getLogger(LivroController.class);

    @GetMapping
    public ResponseEntity<List<LivroDto>> listLivros(
            @RequestParam String volumes
    ) {
        RestClient client = RestClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String raw = client.get()
                .uri("/volumes?q=" + volumes)
                .retrieve()
                .body(String.class);

        log.info("Resposta da API: " + raw);

        LivroApiExternoDto response = client.get()
                .uri("/volumes?q=" + volumes)
                .retrieve()
                .body(LivroApiExternoDto.class);

        if (response == null) {
            return ResponseEntity.noContent().build();
        }

        List<LivroDto> resposta =
                response.getItems().stream().map(item -> {
                    LivroDto bancoDto = new LivroDto();
                    bancoDto.setId(item.getId());
                    bancoDto.setTitle(item.getTitle());
                    bancoDto.setAuthors(item.getAuthors());
                    bancoDto.setPublishedDate(item.getPublishedDate());
                    bancoDto.setThumbnail(item.getThumbnail());
                    bancoDto.setLanguage(item.getLanguage());
                    bancoDto.setPageCount(item.getPageCount());
                    bancoDto.setPreviewLink(item.getPreviewLink());
                    bancoDto.setInfoLink(item.getInfoLink());
                    bancoDto.setDownloadLinkPdf(item.getDownloadLinkPdf());
                    return bancoDto;
                }).toList();
        return ResponseEntity.ok(resposta);

    }

}
