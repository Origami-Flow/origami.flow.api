package origami_flow.salgado_trancas_api.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.service.LoginService;

@RequestMapping("/logins")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @Operation(
            summary = "Autenticar cliente",
            description = "Autentica um cliente com credenciais válidas e retorna um token JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida, token JWT retornado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtTokenResponse.class))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação, credenciais inválidas")
    })
    @PostMapping("/cliente")
    public ResponseEntity<JwtTokenResponse> autenticarCliente(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(loginService.autenticarCliente(loginRequestDTO));
    }

    @Operation(
            summary = "Autenticar trancista",
            description = "Autentica uma trancista com credenciais válidas e retorna um token JWT."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida, token JWT retornado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = JwtTokenResponse.class))),
            @ApiResponse(responseCode = "401", description = "Falha na autenticação, credenciais inválidas")
    })
    @PostMapping("/trancista")
    public ResponseEntity<JwtTokenResponse> autenticarTrancista(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(loginService.autenticarTrancista(loginRequestDTO));
    }
}
