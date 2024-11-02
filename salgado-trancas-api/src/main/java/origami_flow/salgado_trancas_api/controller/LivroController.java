package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class LivroController {
    private static final Logger log = LoggerFactory.getLogger(LivroController.class);
    @Autowired
    private LivroService livroService;

    @Operation(
            summary = "Listar livros por título",
            description = "Busca livros com um título específico, com opção de ordenação."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de livros retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = LivroDto.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum livro encontrado com o título especificado"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos fornecidos na requisição")
    })
    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<List<LivroDto>> listLivros(
            @Parameter(description = "Título ou parte do título do livro para a busca", example = "Machado", required = true)
            @RequestParam String title,
            @Parameter(description = "Ordem de classificação dos resultados (ex: asc para ascendente, desc para descendente ou none sem ordenação) pelo título do livro.", example = "asc")
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