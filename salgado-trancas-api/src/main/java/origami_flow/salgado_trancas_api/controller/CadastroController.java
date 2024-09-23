package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.ClienteRequestDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.service.CadastroService;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;
    
    @PostMapping("/cliente")
    public ResponseEntity<Cliente> cadastrarUsuario(@RequestBody @Valid Cliente cliente){
        Cliente clienteRetorno = cadastroService.cadastrarUsuario(cliente);

        return ResponseEntity.status(201).body(clienteRetorno);
    }

    @PostMapping("/trancista")
    public ResponseEntity<Trancista> cadastrarTrancista(@RequestBody @Valid Trancista novoTrancista) {
        Trancista trancistaRetorno = cadastroService.cadastrarTrancista(novoTrancista);
        return ResponseEntity.status(201).body(trancistaRetorno);
    }
}
