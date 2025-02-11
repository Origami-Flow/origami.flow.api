package origami_flow.salgado_trancas_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import origami_flow.salgado_trancas_api.dto.FileDTO;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.service.CloudinaryService;

@RequestMapping("/file")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final CloudinaryService cloudinaryService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDTO> uploadFile(
          @ModelAttribute @Valid FileRequestDTO fileRequestDTO) {
        log.info("Uploading Files to Storage");
        return ResponseEntity.status(HttpStatus.CREATED)
              .body(cloudinaryService.uploadFile(fileRequestDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<FileDTO> updateFile(FileRequestDTO fileRequestDTO) {
        log.info("Updating Files to Storage");
        return ResponseEntity.status(HttpStatus.OK)
              .body(cloudinaryService.uploadFile(fileRequestDTO));
    }
}
