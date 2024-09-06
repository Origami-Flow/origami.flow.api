package origami_flow.salgado_trancas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.service.LoginService;

@RequestMapping("/logins")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<UsuarioAbstract> autenticar(@RequestParam String email, @RequestParam String senha) {
        UsuarioAbstract usuarioRetorno = loginService.autenticar(email,senha);
        if (usuarioRetorno == null) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioRetorno);
    }
}
