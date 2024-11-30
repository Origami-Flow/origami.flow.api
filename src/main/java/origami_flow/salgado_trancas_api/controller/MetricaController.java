package origami_flow.salgado_trancas_api.controller;

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

    @GetMapping
    public ResponseEntity<MetricasResponseDTO> buscarMetricas(@RequestParam int mes, @RequestParam int ano) {
        return ResponseEntity.ok(metricaService.buscarDadosParaMetrica(mes, ano));
    }

}
