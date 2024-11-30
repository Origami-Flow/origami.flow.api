package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.response.AtendimentoRealizadoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.mapper.AtendimentoRealizadoMapper;
import origami_flow.salgado_trancas_api.service.AtendimentoRealizadoService;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/atendimento-realizado")
@RestController
@SecurityRequirement(name = "bearerAuth")
public class AtendimentoRealizadoController {

    private final AtendimentoRealizadoService atendimentoRealizadoService;
    private final AtendimentoRealizadoMapper atendimentoRealizadoMapper;

    @GetMapping
    public ResponseEntity<List<AtendimentoRealizadoDetalheResponseDTO>> listarAtendimentosRealizados(){
        List<AtendimentoRealizado> lista = atendimentoRealizadoService.listarAtendimentosRealizados();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(atendimentoRealizadoMapper::toDto).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<AtendimentoRealizadoDetalheResponseDTO> atendimentoRealizadoPorId(@PathVariable Integer id){
        AtendimentoRealizado atendimentoRealizado = atendimentoRealizadoService.atendimentoRealizadoPorId(id);
        return ResponseEntity.ok(atendimentoRealizadoMapper.toDto(atendimentoRealizado));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarAtendimentoRealizado(@PathVariable Integer id){
        atendimentoRealizadoService.apagarAtendimentoRealizado(id);
        return ResponseEntity.noContent().build();
    }
}
