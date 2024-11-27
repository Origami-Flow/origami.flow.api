package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.DespesaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.DespesaDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Despesa;
import origami_flow.salgado_trancas_api.mapper.DespesaMapper;
import origami_flow.salgado_trancas_api.service.DespesaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/despesas")
public class DespesaController {

    private final DespesaService despesaService;

    private final DespesaMapper despesaMapper;

    @GetMapping
    public ResponseEntity<List<DespesaDetalheResponseDTO>> listarDespesas(){
        List<Despesa> despesas = despesaService.listarTodasDepespesas();
        List<DespesaDetalheResponseDTO> despesaResposta = despesas.stream().map(despesaMapper::toDespesaDetalheResponseDTO).toList();

        if (despesas.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(despesas.stream().map(despesaMapper::toDespesaDetalheResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDetalheResponseDTO> despesaPorId(@PathVariable Integer id){
        Despesa despesa = despesaService.despesaPorId(id);

        return ResponseEntity.ok().body(despesaMapper.toDespesaDetalheResponseDTO(despesa));
    }

    @PostMapping
    public ResponseEntity<DespesaDetalheResponseDTO> criarNovaDespesa(@RequestBody @Valid DespesaRequestDTO despesaRequestDTO){
        Despesa despesa = despesaService.cadastrarDespesa(despesaMapper.toDespesaEntity(despesaRequestDTO),despesaRequestDTO.getIdProduto(),despesaRequestDTO.getIdCaixa());
        return ResponseEntity.created(null).body(despesaMapper.toDespesaDetalheResponseDTO(despesa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaDetalheResponseDTO> atualizarDespesa(@RequestBody @Valid DespesaRequestDTO despesaRequestDTO, @PathVariable Integer id){
        Despesa despesa = despesaService.atualizarDespesa(id,despesaMapper.toDespesaEntity(despesaRequestDTO));
        return ResponseEntity.ok(despesaMapper.toDespesaDetalheResponseDTO(despesa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletearDespesa(@PathVariable Integer id){
        despesaService.deletarDespesa(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/total-desepesa")
    public ResponseEntity<Double> totalDespesaMensal(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
        return ResponseEntity.ok(despesaService.totalDespesasMensal(inicio, fim));
    }



}
