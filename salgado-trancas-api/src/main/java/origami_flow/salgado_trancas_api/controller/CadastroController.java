package origami_flow.salgado_trancas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.service.CadastroService;

@RestController
@RequestMapping("/cadastros")
public class CadastroController {
    @Autowired
    private CadastroService cadastroService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarUsuario(@RequestBody Cliente cliente){

        cadastroService.cadastrarUsuario(cliente);
        return ResponseEntity.status(201).body(cliente);
    }
}
