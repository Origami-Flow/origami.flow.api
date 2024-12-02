package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.response.FinancaResponseDTO;
import origami_flow.salgado_trancas_api.service.FinancaService;

import java.time.LocalDate;
import java.util.Queue;
import java.util.Stack;

@RestController
@RequestMapping("/financas")
@RequiredArgsConstructor
public class FinancaController {

    private final FinancaService financaService;

    @Operation(
            summary = "Calcular Finanças",
            description = "Retorna um relatório financeiro mensal baseado no mes e ano."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Relatório financeiro gerado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = FinancaResponseDTO.class)) // Tipo de retorno em JSON
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parâmetros de intervalo inválidos"
            )
    })
    @GetMapping
    public ResponseEntity<FinancaResponseDTO> calcularFinancas(
            @RequestParam int mes,
            @RequestParam int ano
    ) {

        FinancaResponseDTO financa = financaService.gerarRelatorioMensal(mes, ano);
        return ResponseEntity.ok(financa);
    }

    @GetMapping("/pilha-despesas")
    public String exibirPilhaDespesas() {
        return financaService.exibirPilhaDespesas();
    }

    @GetMapping("/fila-atendimentos")
    public String exibirFilaAtendimentos() {
        return financaService.exibirFilaAtendimentos();
    }

}
