package origami_flow.salgado_trancas_api.service;

import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.dto.FileDTO;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public FileDTO uploadFile(FileRequestDTO file) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", file.getPath());
            options.put("display_name", file.getName());
            return (FileDTO) cloudinary.uploader().upload(file.getFile().getBytes(), options);
        } catch (IOException e) {
            log.error("Erro ao fazer upload do arquivo: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                  "Erro ao fazer upload do arquivo");
        }
    }

    public FileDTO updateFile(FileRequestDTO file) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", file.getPath());
            options.put("display_name", file.getName());
            return (FileDTO) cloudinary.uploader().upload(file.getFile().getBytes(), options);
        } catch (IOException e) {
            log.error("Erro ao fazer update do arquivo: {}", e.getMessage());
            throw new EntidadeNaoEncontradaException("Erro ao fazer upload do arquivo");
        }
    }
}
