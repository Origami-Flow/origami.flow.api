package origami_flow.salgado_trancas_api.controller;


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

    @PostMapping("/cliente")
    public ResponseEntity<JwtTokenResponse> autenticarCliente(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.autenticarCliente(loginRequestDTO);
    }

    @PostMapping("/trancista")
    public ResponseEntity<JwtTokenResponse> autenticarTrancista(@RequestBody LoginRequestDTO loginRequestDTO) {
        return loginService.autenticarTrancista(loginRequestDTO);
    }
}
