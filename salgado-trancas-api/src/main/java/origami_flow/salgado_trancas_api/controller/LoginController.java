package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.service.LoginService;

@RequestMapping("/logins")
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping
    public ResponseEntity<UsuarioAbstract> autenticar(@RequestBody UsuarioAbstract usuario) {
        UsuarioAbstract usuarioRetorno = loginService.autenticar(usuario.getEmail(), usuario.getSenha());

        return ResponseEntity.ok(usuarioRetorno);
    }
}
