package origami_flow.salgado_trancas_api.controller;

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
        if (listaClientes.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(listaClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Integer id) {
        Cliente clienteResponse = clienteService.listarClientePorId(id);
        if (clienteResponse == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarClientePorId(@PathVariable Integer id, @RequestBody Cliente clienteAtualizado) {
        Cliente clienteResponse = clienteService.atualizarCliente(id, clienteAtualizado);
        if (clienteResponse == null) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(clienteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.status(200).build();
    }
}
