package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.ServicoAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ServicoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.ServicoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.mapper.ServicoMapper;
import origami_flow.salgado_trancas_api.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/servicos")
@RequiredArgsConstructor
public class ServicoController {

    private final ServicoService servicoService;

    private final ServicoMapper servicoMapper;

    @GetMapping
    public ResponseEntity<List<ServicoDetalheResponseDTO>> buscarServico() {
        List<Servico> lista = servicoService.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista.stream().map(servicoMapper::toResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDetalheResponseDTO> buscarPorId(@PathVariable Integer id) {
        Servico servico = servicoService.servicoPorId(id);
        return ResponseEntity.ok(servicoMapper.toResponseDTO(servico));
    }

    @PostMapping
    public ResponseEntity<ServicoDetalheResponseDTO> criarServico(@RequestBody @Valid ServicoRequestDTO servicoRequestDTO) {
        Servico servico = servicoService.criarServico(servicoMapper.toEntity(servicoRequestDTO));
        return ResponseEntity.created(null).body(servicoMapper.toResponseDTO(servico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoDetalheResponseDTO> atualizarServico(@PathVariable Integer id, @RequestBody ServicoAtualizacaoRequestDTO servicoAtualizacaoRequestDTO) {
        Servico servico = servicoService.atualizarServico(servicoMapper.toEntity(servicoAtualizacaoRequestDTO), id);
        return ResponseEntity.ok(servicoMapper.toResponseDTO(servico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerServico(@PathVariable Integer id) {
        servicoService.removerServico(id);
        return ResponseEntity.noContent().build();
    }
}