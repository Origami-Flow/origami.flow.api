package origami_flow.salgado_trancas_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.entity.Atendimento;

@RestController
public class AtendimentoController {

    @PostMapping
    public void criarAtendimento(@RequestBody Atendimento atendimento) {

    }
}
