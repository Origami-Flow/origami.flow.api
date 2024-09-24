package origami_flow.salgado_trancas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioLoginDto;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioTokenDto;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.service.LoginService;

@RequestMapping("/logins")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuario) {
        UsuarioTokenDto usuarioRetorno = this.loginService.autenticar(usuario);

        return ResponseEntity.ok(usuarioRetorno);
    }
}
