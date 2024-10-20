package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.SalaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.SalaoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.mapper.SalaoMapper;
import origami_flow.salgado_trancas_api.service.SalaoService;

import java.util.List;

@RestController
@RequestMapping("/saloes")
@RequiredArgsConstructor
public class SalaoController {

    private final SalaoService salaoService;

    private final SalaoMapper salaoMapper;

    @PostMapping
    public ResponseEntity<SalaoDetalheResponseDTO> cadastrarSalao(@RequestBody @Valid SalaoRequestDTO salaoRequestDTO) {
        Salao salao = salaoService.cadastrarSalao(salaoMapper.toSalaoEntity(salaoRequestDTO), salaoRequestDTO.getCep());
        return ResponseEntity.created(null).body(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @GetMapping
    public ResponseEntity<List<SalaoDetalheResponseDTO>> listarSaloes() {
        List<Salao> saloes = salaoService.listar();
        if (saloes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(saloes.stream().map(salaoMapper::toSalaoDetalheResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaoDetalheResponseDTO> buscarSalao(@PathVariable Integer id) {
        Salao salao = salaoService.salaoPorId(id);
        return ResponseEntity.ok(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalaoDetalheResponseDTO> atualizarSalao(@PathVariable Integer id, @RequestBody @Valid SalaoRequestDTO salaoRequestDTO) {
        Salao salao = salaoService.atualizar(id, salaoMapper.toSalaoEntity(salaoRequestDTO));
        return ResponseEntity.ok(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }

    @PutMapping("/{id}/endereco")
    public ResponseEntity<SalaoDetalheResponseDTO> atualizarEnderecoSalao(@PathVariable Integer id, @RequestParam String cep) {
        Salao salao = salaoService.atualizarEndereco(id, cep);
        return ResponseEntity.ok(salaoMapper.toSalaoDetalheResponseDTO(salao));
    }
}
