package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.response.FinancaResponseDTO;
import origami_flow.salgado_trancas_api.service.FinancaService;

import java.time.LocalDate;

@RestController
@RequestMapping("/financas")
@RequiredArgsConstructor
public class FinancaController {

    private final FinancaService financaService;

    @GetMapping
    public ResponseEntity<FinancaResponseDTO> calcularFinancas(@RequestParam  int inicio, @RequestParam  int fim) {
        FinancaResponseDTO financa = financaService.gerarRelatorioMensal(inicio, fim);
        return ResponseEntity.ok(financa);
    }
}
