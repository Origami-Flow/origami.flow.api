package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioCriacaoDto;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.service.CadastroService;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;
    
    @PostMapping("/cliente")
    @SecurityRequirement(name="Bearer")
    public ResponseEntity<Cliente> cadastrarUsuario(@RequestBody @Valid UsuarioCriacaoDto cliente){
        Cliente clienteRetorno = cadastroService.cadastrarUsuario(cliente);

        return ResponseEntity.status(201).body(clienteRetorno);

    }

    @PostMapping("/trancista")
    public ResponseEntity<Trancista> cadastrarTrancista(@RequestBody @Valid Trancista novoTrancista) {
        Trancista trancistaRetorno = cadastroService.cadastrarTrancista(novoTrancista);
        return ResponseEntity.status(201).body(trancistaRetorno);
    }
}
