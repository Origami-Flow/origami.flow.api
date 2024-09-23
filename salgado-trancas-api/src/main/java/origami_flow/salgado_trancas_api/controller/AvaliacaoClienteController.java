package origami_flow.salgado_trancas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.service.AvaliacaoClienteService;

@RestController
@RequestMapping("/avaliacao-usuarios")
public class AvaliacaoClienteController {
    @Autowired
    private AvaliacaoClienteService avaliacaoClienteService;


}
