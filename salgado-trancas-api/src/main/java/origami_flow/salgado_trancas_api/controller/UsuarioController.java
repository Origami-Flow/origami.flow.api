package origami_flow.salgado_trancas_api.controller;

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
        if (listaClientes.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(listaClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarClientePorId(@PathVariable Integer id) {
        Usuario clienteRetorno = usuarioService.listarClientePorId(id);
        if (clienteRetorno == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(clienteRetorno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarClientePorId(@PathVariable Integer id, @RequestBody Usuario clienteAtualizado) {
        Usuario clienteRetorno = usuarioService.atualizarCliente(id, clienteAtualizado);
        if (clienteRetorno == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(clienteRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        usuarioService.deletarCliente(id);
        return ResponseEntity.status(204).build();
    }
}
