package origami_flow.salgado_trancas_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.service.TrancistaService;

import java.util.List;

@RestController
@RequestMapping("/trancistas")
public class TrancistaController {
    @Autowired
    TrancistaService trancistaService;

    @GetMapping
    public ResponseEntity<List<Trancista>> listarTrancista() {
        List<Trancista> listaTrancistas = trancistaService.listarTrancista();
        if (listaTrancistas.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(listaTrancistas);
    }

    @PostMapping
    public ResponseEntity<Trancista> cadastrar(@RequestBody Trancista novoTrancista) {
        Trancista trancistaResponse = trancistaService.cadastrar(novoTrancista);
        return ResponseEntity.status(201).body(trancistaResponse);
    }
}
