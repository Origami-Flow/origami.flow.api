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
import origami_flow.salgado_trancas_api.dto.ApiLivroDto;
import origami_flow.salgado_trancas_api.dto.LivroApiExternoDto;
import origami_flow.salgado_trancas_api.dto.LivroDto;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private static final Logger log = LoggerFactory.getLogger(LivroController.class);

    @GetMapping
    public ResponseEntity<List<LivroDto>> listLivros(
            @RequestParam String title
    ) {
        RestClient client = RestClient.builder()
                .baseUrl("https://openlibrary.org/search.json")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        String raw = client.get()
                .uri("?title=" + title)
                .retrieve()
                .body(String.class);

        log.info("Resposta da API: " + raw);

        List<LivroDto> response = client.get()
                .uri("?title=" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<List<LivroDto>>() {});

        if(response == null ){
            return ResponseEntity.noContent().build();
        }

        List<LivroDto> resposta = response.stream().map(item ->LivroDto.builder()
//                .isbn(item.getIsbn())
                .authors(item.getAuthors())
//                .publishDate(item.getPublishDate())
//                .coverUrl(item.getCoverUrl())
//                .subjects(item.getSubjects())
//                .subtitle(item.getSubtitle())
                .title(item.getTitle())
                .build()).toList();

        return ResponseEntity.ok(resposta);

    }

}
