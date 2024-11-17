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
import origami_flow.salgado_trancas_api.exceptions.RequisicaoErradaException;
import origami_flow.salgado_trancas_api.mapper.LivroMapper;

import java.util.List;

@Service
public class LivroService {
    public List<LivroDto> buscarLivrosPorTitulo(String title, String order) {
        if (!order.equals("asc") && !order.equals("desc") && !order.equals("none")) {
            throw new RequisicaoErradaException("Ordenação incorreta");
        }
        RestClient client = RestClient.builder()
                .baseUrl("https://www.googleapis.com/books/v1/volumes?q=" + title )
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(new MappingJackson2HttpMessageConverter()))
                .build();

        LivroApiExternoDto result = client.get()
                .uri("?title=" + title)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });

        if (result == null || result.getItems() == null) {
            return List.of();
        }

        List<Item> items = result.getItems();
        List<LivroDto> livros = LivroMapper.toLivroDtoList(items);

        if (order.equals("asc") || order.equals("desc")) {
            ordenarLivros(livros, order);
        }
        return livros;
    }

    private List<LivroDto> ordenarLivros(List<LivroDto> livros, String order) {
        for (int i = 0; i < livros.size() - 1; i++) {
            int posicaoValor = i;
            for (int j = i + 1; j < livros.size(); j++) {
                if (order.equals("asc")) {
                    if (livros.get(posicaoValor).getTitle().compareTo(livros.get(j).getTitle()) > 0) {
                        posicaoValor = j;
                    }
                } else {
                    if (livros.get(posicaoValor).getTitle().compareTo(livros.get(j).getTitle()) < 0) {
                        posicaoValor = j;
                    }
                }
            }
            LivroDto aux = livros.get(posicaoValor);
            livros.set(posicaoValor, livros.get(i));
            livros.set(i, aux);
        }
        return livros;
    }

}
