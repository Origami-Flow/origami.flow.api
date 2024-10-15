package origami_flow.salgado_trancas_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.service.TrancistaService;

import java.util.List;

@RestController
@RequestMapping("/trancistas")
@RequiredArgsConstructor
public class TrancistaController {

    private final TrancistaService trancistaService;

    @GetMapping
    public ResponseEntity<List<Trancista>> listarTrancista() {
        List<Trancista> listaTrancistas = trancistaService.listarTrancista();
        if (listaTrancistas.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(listaTrancistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trancista> trancistaPorId(@PathVariable Integer id) {
        Trancista trancistaRetorno = trancistaService.trancistaPorId(id);

        return ResponseEntity.ok(trancistaRetorno);
    }

}
