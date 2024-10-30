package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.mapper.ClienteMapper;
import origami_flow.salgado_trancas_api.service.CadastroService;

@RestController
@RequestMapping("/cadastros")
@RequiredArgsConstructor
public class CadastroController {

    private final ClienteMapper clienteMapper;
    private final CadastroService cadastroService;
    
    @PostMapping("/cliente")
    public ResponseEntity<ClienteDetalheResponseDTO> cadastrarUsuario(@RequestBody @Valid CadastroRequestDTO cadastroRequestDTO) {
        Cliente clienteRetorno = cadastroService.cadastrarCliente(cadastroRequestDTO, cadastroRequestDTO.getCep());
        return ResponseEntity.created(null).body(clienteMapper.toClienteDetalheResponseDTO(clienteRetorno));
    }

    @PostMapping("/trancista")
    public ResponseEntity<Trancista> cadastrarTrancista(@RequestBody @Valid Trancista novoTrancista) {
        Trancista trancistaRetorno = cadastroService.cadastrarTrancista(novoTrancista);
        return ResponseEntity.status(201).body(trancistaRetorno);
    }
}