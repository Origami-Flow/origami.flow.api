package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.AvaliacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.AvaliacaoDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Avaliacao;
import origami_flow.salgado_trancas_api.mapper.AvaliacaoMapper;
import origami_flow.salgado_trancas_api.service.AvaliacaoClienteService;

import java.util.List;

@RestController
@RequestMapping("/avaliacao-usuarios")
@RequiredArgsConstructor
public class AvaliacaoClienteController {

    private final AvaliacaoClienteService avaliacaoClienteService;
    private final AvaliacaoMapper avaliacaoMapper;


    @GetMapping
    public ResponseEntity<List<AvaliacaoDetalheResponseDTO>> listarAvaliacao(){
        List<Avaliacao> lista = avaliacaoClienteService.listarAaliacao();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(lista.stream().map(avaliacaoMapper::toAvaliacaoDetalheResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoDetalheResponseDTO> avaliacaoPorId(@PathVariable Integer id){
        Avaliacao avaliacao = avaliacaoClienteService.avaliacaoPorId(id);

        return ResponseEntity.ok(avaliacaoMapper.toAvaliacaoDetalheResponseDTO(avaliacao));
    }

    @PostMapping
    public ResponseEntity<AvaliacaoDetalheResponseDTO> criarAvaliacao(@RequestBody @Valid AvaliacaoRequestDTO avaliacao){
        Avaliacao avaliacaoSalvar = avaliacaoClienteService.criarAvaliacao(avaliacaoMapper.toEntity(avaliacao), avaliacao.getAtendimentoRealizadoId(),avaliacao.getClienteId(),avaliacao.getSalaoId());

        return ResponseEntity.created(null).body(avaliacaoMapper.toAvaliacaoDetalheResponseDTO(avaliacaoSalvar));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoDetalheResponseDTO> atualizarAvaliacao(@PathVariable @Valid Integer id, @RequestBody AvaliacaoRequestDTO avaliacaoRequestDTO){
        Avaliacao avaliacao = avaliacaoClienteService.atualizarAvaliacao(id, avaliacaoMapper.toEntity(avaliacaoRequestDTO),avaliacaoRequestDTO.getAtendimentoRealizadoId(),avaliacaoRequestDTO.getClienteId(),avaliacaoRequestDTO.getSalaoId());
        return ResponseEntity.ok(avaliacaoMapper.toAvaliacaoDetalheResponseDTO(avaliacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Integer id){
        avaliacaoClienteService.deletarAvaliacao(id);
        return ResponseEntity.noContent().build();
    }

}
