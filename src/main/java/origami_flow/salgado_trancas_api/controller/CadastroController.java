package origami_flow.salgado_trancas_api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.request.CadastroClienteSimplesRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.TrancistaRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteResponseDTO;
import origami_flow.salgado_trancas_api.dto.response.trancista.TrancistaDetalheResposeDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.mapper.ClienteMapper;
import origami_flow.salgado_trancas_api.mapper.TrancistaMapper;
import origami_flow.salgado_trancas_api.service.CadastroService;

@RestController
@RequestMapping("/cadastros")
@RequiredArgsConstructor
public class CadastroController {

    private final ClienteMapper clienteMapper;

    private final TrancistaMapper trancistaMapper;

    private final CadastroService cadastroService;

    @Operation(summary = "Cadastra um novo cliente", description = "Endpoint para cadastrar um novo cliente a partir das informações enviadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDetalheResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/cliente")
    public ResponseEntity<ClienteDetalheResponseDTO> cadastrarUsuario(@RequestBody @Valid CadastroRequestDTO cadastroRequestDTO) {
        Cliente clienteRetorno = cadastroService.cadastrarCliente(cadastroRequestDTO, cadastroRequestDTO.getCep());
        return ResponseEntity.created(null).body(clienteMapper.toClienteDetalheResponseDTO(clienteRetorno));
    }

    @Operation(summary = "Cadastra uma nova trancista", description = "Endpoint para cadastrar uma trancista com base nos dados fornecidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Trancista cadastrada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = TrancistaDetalheResposeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro de validação",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/trancista")
    public ResponseEntity<TrancistaDetalheResposeDTO> cadastrarTrancista(@RequestBody @Valid TrancistaRequestDTO trancistaRequestDTO) {
        Trancista trancistaRetorno = cadastroService.cadastrarTrancista(trancistaMapper.toEntity(trancistaRequestDTO));
        return ResponseEntity.created(null).body(trancistaMapper.toDTO(trancistaRetorno));
    }

    @PostMapping("/cliente-simples")
    public ResponseEntity<ClienteResponseDTO> cadastroSimplesCliente(@RequestBody @Valid CadastroClienteSimplesRequestDTO cadastroClienteSimplesRequestDTO) {
        Cliente clienteRetorno = cadastroService.cadastrarClienteSimples(cadastroClienteSimplesRequestDTO);
        return ResponseEntity.created(null).body(clienteMapper.toClienteResponseDTO(clienteRetorno));
    }
}