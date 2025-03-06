package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.entity.Imagem;
import origami_flow.salgado_trancas_api.service.ImagemService;

@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImagemService imagemService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Imagem> uploadFile(
          @ModelAttribute @Valid FileRequestDTO fileRequestDTO) {
        log.info("Uploading Files to Storage");
        return ResponseEntity.status(HttpStatus.CREATED)
              .body(imagemService.uploadFile(fileRequestDTO));
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Imagem> updateFile(@ModelAttribute FileRequestDTO fileRequestDTO,
          @PathVariable Integer id) {
        log.info("Updating Files to Storage");
        return ResponseEntity.status(HttpStatus.OK)
              .body(imagemService.updateFile(fileRequestDTO, id));
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Integer id) {
        log.info("Deleting Files from Storage");
        imagemService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }
}
