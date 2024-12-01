package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.response.MetricasResponseDTO;
import origami_flow.salgado_trancas_api.service.MetricaService;

@RestController
@RequestMapping("/metricas")
@RequiredArgsConstructor
public class MetricaController {

    private final MetricaService metricaService;

    @Operation(
            summary = "Buscar Métricas",
            description = "Retorna as métricas mensais com base no mês e ano fornecidos."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Métricas encontradas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MetricasResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetros de mês ou ano inválidos"
            )
    })
    @GetMapping
    public ResponseEntity<MetricasResponseDTO> buscarMetricas(
            @RequestParam int mes,
            @RequestParam int ano
    ) {
        return ResponseEntity.ok(metricaService.buscarDadosParaMetrica(mes, ano));
    }

}
