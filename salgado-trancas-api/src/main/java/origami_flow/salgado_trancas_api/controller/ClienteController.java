package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.cliente.ClienteRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.endereco.EnderecoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.mapper.ClienteMapper;
import origami_flow.salgado_trancas_api.mapper.EnderecoMapper;
import origami_flow.salgado_trancas_api.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteMapper clienteMapper;
    private final EnderecoMapper enderecoMapper;
    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDetalheResponseDTO>> listarCliente() {
        List<Cliente> listaClientes = clienteService.listarCliente();
        if (listaClientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaClientes.stream().map(clienteMapper::toClienteDetalheResponseDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetalheResponseDTO> clientePorId(@PathVariable Integer id) {
        Cliente clienteRetorno = clienteService.clientePorId(id);
        return ResponseEntity.ok(clienteMapper.toClienteDetalheResponseDTO(clienteRetorno));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody @Valid ClienteRequestDTO clienteDTO) {
        Cliente clienteRetorno = clienteService.atualizarCliente(id, clienteMapper.toClienteEntity(clienteDTO));
        return ResponseEntity.ok(clienteRetorno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<ClienteDetalheResponseDTO> cadastrarEndereco(@PathVariable Integer id, @RequestBody @Valid EnderecoRequestDTO enderecoDTO) {
        Cliente cliente = clienteService.cadastrarEndereco(id, enderecoMapper.toEnderecoEntity(enderecoDTO));
        return ResponseEntity.created(null).body(clienteMapper.toClienteDetalheResponseDTO(cliente));
    }

    @PutMapping("/{id}/enderecos")
    public ResponseEntity<ClienteDetalheResponseDTO> atualizarEndereco(@PathVariable Integer id, @RequestBody @Valid EnderecoRequestDTO enderecoDTO) {
        Cliente cliente = clienteService.atualizarEndereco(id, enderecoMapper.toEnderecoEntity(enderecoDTO));
        return ResponseEntity.ok(clienteMapper.toClienteDetalheResponseDTO(cliente));
    }
}
