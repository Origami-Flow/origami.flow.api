package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.response.AuxiliarDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Auxiliar;
import origami_flow.salgado_trancas_api.mapper.AuxiliarMapper;
import origami_flow.salgado_trancas_api.service.AuxiliarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auxiliares")
public class AuxiliarController {

    private final AuxiliarService auxiliarService;

    private final AuxiliarMapper auxiliarMapper;

    @GetMapping
    public ResponseEntity<List<AuxiliarDetalheResponseDTO>> listarAuxiliar() {
        List<Auxiliar> lista = auxiliarService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(auxiliarMapper::toDto).toList());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<AuxiliarDetalheResponseDTO>> listarAuxiliarPorNome(@RequestParam String nome) {
        List<Auxiliar> lista = auxiliarService.listarPorNome(nome);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(auxiliarMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuxiliarDetalheResponseDTO> buscarAuxiliarPorId(@PathVariable Integer id) {

    }
}
