package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.request.ClienteAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.mapper.ClienteMapper;
import origami_flow.salgado_trancas_api.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ClienteController {

    private final ClienteMapper clienteMapper;

    private final ClienteService clienteService;

    @Operation(summary = "Lista todos os clientes", description = "Retorna uma lista de todos os clientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de clientes encontrada",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhum cliente encontrado")
    })
    @GetMapping
    public ResponseEntity<List<ClienteDetalheResponseDTO>> listarCliente() {
        List<Cliente> listaClientes = clienteService.listarCliente();
        if (listaClientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaClientes.stream().map(clienteMapper::toClienteDetalheResponseDTO).toList());
    }

    @Operation(summary = "Busca cliente por ID", description = "Retorna os detalhes de um cliente específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetalheResponseDTO> clientePorId(@PathVariable Integer id) {
        Cliente clienteRetorno = clienteService.clientePorId(id);
        return ResponseEntity.ok(clienteMapper.toClienteDetalheResponseDTO(clienteRetorno));
    }

    @Operation(summary = "Atualiza um cliente", description = "Atualiza as informações de um cliente existente com base em seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id, @RequestBody @Valid ClienteAtualizacaoRequestDTO clienteAtualizacaoRequestDTO) {
        Cliente clienteRetorno = clienteService.atualizarCliente(id, clienteMapper.toClienteEntity(clienteAtualizacaoRequestDTO));
        return ResponseEntity.ok(clienteRetorno);
    }

    @Operation(summary = "Deleta um cliente", description = "Remove um cliente do sistema com base em seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza o endereço de um cliente", description = "Atualiza o endereço de um cliente com base no ID e CEP fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    @PutMapping("/{id}/endereco")
    public ResponseEntity<ClienteDetalheResponseDTO> atualizarEndereco(@PathVariable Integer id, @RequestParam String cep) {
        Cliente cliente = clienteService.atualizarEndereco(id,cep);
        return ResponseEntity.ok(clienteMapper.toClienteDetalheResponseDTO(cliente));
    }

    @GetMapping("/nome-cliente")
    public ResponseEntity<List<ClienteDetalheResponseDTO>> listarNomeCliente(@RequestParam String nome) {
        List<Cliente> clientes = clienteService.listarPorNome(nome);
        if (clientes.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(clientes.stream().map(clienteMapper::toClienteDetalheResponseDTO).toList());
    }
}
