package origami_flow.salgado_trancas_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import origami_flow.salgado_trancas_api.controller.LivroController;
import origami_flow.salgado_trancas_api.dto.LivroApiExternoDto;
import origami_flow.salgado_trancas_api.dto.LivroDto;
import origami_flow.salgado_trancas_api.dto.livro.Item;
import origami_flow.salgado_trancas_api.mapper.LivroMapper;

import java.util.List;

@Service
public class LivroService {
    private static final Logger log = LoggerFactory.getLogger(LivroController.class);

    public List<LivroDto> buscarLivrosPorTitulo(String title) {
        RestClient client = RestClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1/volumes?q=" + title + "&filter=free-ebooks&langRestrict=pt-BR")
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        LivroApiExternoDto result = client.get()
                .uri("?title=" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (result == null || result.getItems() == null) {
            return List.of();
        }

        List<Item> items = result.getItems();
        return LivroMapper.toLivroDtoList(items);
    }


}
