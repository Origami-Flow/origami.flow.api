package jwt.api.uso.controller.usuario;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jwt.api.service.usuario.autenticacao.dto.CadastroCriptografado;
import jwt.api.service.usuario.autenticacao.dto.LoginRequestDTO;
import jwt.api.service.usuario.autenticacao.dto.JwtTokenResponse;
import jwt.api.service.usuario.autenticacao.dto.CadastroRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jwt.api.service.usuario.AutenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticationController {

    private final AutenticationService autenticationService;

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroCriptografado> criar(@RequestBody @Valid CadastroRequestDTO cadastroRequestDTO){
        return ResponseEntity.created(null).body(autenticationService.criar(cadastroRequestDTO));
    }

    @PostMapping("/login/cliente")
    public ResponseEntity<JwtTokenResponse> loginCliente (@RequestBody LoginRequestDTO loginRequestDTO){
        JwtTokenResponse jwtTokenResponse = this.autenticationService.autenticarCliente(loginRequestDTO);
        System.out.println(jwtTokenResponse);
        return ResponseEntity.ok().body(jwtTokenResponse);
    }

    @PostMapping("/login/trancista")
    public ResponseEntity<JwtTokenResponse> loginTrancista (@RequestBody LoginRequestDTO loginRequestDTO){
        JwtTokenResponse jwtTokenResponse = this.autenticationService.autenticarCliente(loginRequestDTO);
        System.out.println(jwtTokenResponse);
        return ResponseEntity.ok().body(jwtTokenResponse);
    }

    @PostMapping("/validation")
    @SecurityRequirement(name = "Bearer")
    public void validar() {

    }
}
