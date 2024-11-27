package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.CaixaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.CaixaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.mapper.CaixaMapper;
import origami_flow.salgado_trancas_api.repository.CaixaRepository;
import origami_flow.salgado_trancas_api.service.CaixaService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/caixas")
public class CaixaController {

    private final CaixaService caixaService;

    private final CaixaMapper caixaMapper;

    @GetMapping
    public ResponseEntity<List<CaixaDetalheResponseDTO>> listarCaixa(){
        List<Caixa> caixas = caixaService.listarTodosCaixas();
        if (caixas.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(caixas.stream().map(caixaMapper::toCaixaDetalheResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaixaDetalheResponseDTO> caixaPorId(@PathVariable Integer id){
        Caixa caixa = caixaService.caixaPorId(id);
        return ResponseEntity.ok(caixaMapper.toCaixaDetalheResponseDTO(caixa));
    }

    @PostMapping
    public ResponseEntity<CaixaDetalheResponseDTO> cadastrarCaixa(@RequestBody @Valid CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = caixaService.cadastrarCaixa(caixaMapper.toCaixaEntity(caixaRequestDTO),caixaRequestDTO.getSalaoId());
        return ResponseEntity.created(null).body(caixaMapper.toCaixaDetalheResponseDTO(caixa));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CaixaDetalheResponseDTO> atualizarCaixa(@PathVariable Integer id, @RequestBody @Valid CaixaRequestDTO caixaRequestDTO){
        Caixa caixa = caixaService.atualizarCaixa(id,caixaMapper.toCaixaEntity(caixaRequestDTO));
        return ResponseEntity.ok(caixaMapper.toCaixaDetalheResponseDTO(caixa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCaixa(@PathVariable Integer id){
        caixaService.deletarCaixa(id);
        return ResponseEntity.noContent().build();
    }
}
