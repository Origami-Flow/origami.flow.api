package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.service.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarCliente() {
        List<Usuario> listaClientes = usuarioService.listarCliente();
        if (listaClientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> clientePorId(@PathVariable Integer id) {
        Usuario clienteRetorno = usuarioService.clientePorId(id);

        return ResponseEntity.ok(clienteRetorno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody @Valid Usuario clienteAtualizado) {
        Usuario clienteRetorno = usuarioService.atualizarCliente(id, clienteAtualizado);

        return ResponseEntity.ok(clienteRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        usuarioService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
