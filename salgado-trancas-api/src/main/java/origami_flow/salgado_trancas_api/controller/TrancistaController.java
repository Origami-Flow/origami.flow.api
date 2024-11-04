package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.service.TrancistaService;

import java.util.List;

@RestController
@RequestMapping("/trancistas")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TrancistaController {

    private final TrancistaService trancistaService;

    @Operation(summary = "Listar todos os trancistas", description = "Retorna uma lista de todos os trancistas cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de trancistas retornada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trancista.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum trancista encontrado")
    })
    @GetMapping
    public ResponseEntity<List<Trancista>> listarTrancista() {
        List<Trancista> listaTrancistas = trancistaService.listarTrancista();
        if (listaTrancistas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaTrancistas);
    }

    @Operation(summary = "Buscar trancista por ID", description = "Retorna um trancista específico com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trancista encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trancista.class))),
            @ApiResponse(responseCode = "404", description = "Trancista não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Trancista> trancistaPorId(@PathVariable Integer id) {
        Trancista trancistaRetorno = trancistaService.trancistaPorId(id);

        return ResponseEntity.ok(trancistaRetorno);
    }

}
