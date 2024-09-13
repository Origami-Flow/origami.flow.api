package origami_flow.salgado_trancas_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import origami_flow.salgado_trancas_api.dto.LivroDto;
import origami_flow.salgado_trancas_api.dto.livro.Item;
import origami_flow.salgado_trancas_api.dto.LivroApiExternoDto;
import origami_flow.salgado_trancas_api.service.LivroService;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private static final Logger log = LoggerFactory.getLogger(LivroController.class);
    @Autowired
    private LivroService livroService;

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<LivroDto>> listLivros(
            @RequestParam String title,
            @RequestParam(defaultValue = "none") String order
    ) {
        log.info("Buscando livros com título: " + title);
        List<LivroDto> livros = livroService.buscarLivrosPorTitulo(title, order);

        if (livros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(livros);

    }
}