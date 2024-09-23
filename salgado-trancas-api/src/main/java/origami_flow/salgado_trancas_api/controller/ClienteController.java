package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarCliente() {
        List<Cliente> listaClientes = clienteService.listarCliente();
        if (listaClientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> clientePorId(@PathVariable Integer id) {
        Cliente clienteRetorno = clienteService.clientePorId(id);

        return ResponseEntity.ok(clienteRetorno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody @Valid Cliente clienteAtualizado) {
        Cliente clienteRetorno = clienteService.atualizarCliente(id, clienteAtualizado);

        return ResponseEntity.ok(clienteRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }
}
