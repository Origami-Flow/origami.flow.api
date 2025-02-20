package origami_flow.salgado_trancas_api.service;

import com.cloudinary.Cloudinary;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.dto.FileDTO;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.entity.Imagem;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.HashMapConverter;
import origami_flow.salgado_trancas_api.mapper.ImagemMapper;
import origami_flow.salgado_trancas_api.repository.ImagemRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImagemService {

    private final Cloudinary cloudinary;
    private final ImagemMapper imagemMapper;
    private final ImagemRepository imagemRepository;

    public Imagem uploadFile(FileRequestDTO file) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", file.getPath());
            options.put("display_name", file.getName());
            FileDTO fileDto = HashMapConverter.convertMapToObject(
                  cloudinary.uploader().upload(file.getFile().getBytes(), options), FileDTO.class);
            return imagemRepository.save(imagemMapper.toEntity(fileDto));
        } catch (IOException e) {
            log.error("Erro ao fazer upload do arquivo: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                  "Erro ao fazer upload do arquivo");
        }
    }

    public Imagem findById(Integer id) {
        return imagemRepository.findById(id)
              .orElseThrow(() -> new EntidadeNaoEncontradaException("Imagem não encontrada"));
    }

    public List<Imagem> findAll() {
        return imagemRepository.findAll();
    }

    public FileDTO updateFile(FileRequestDTO file, Integer id) {
        var imagem = findById(id);
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", file.getPath());
            options.put("display_name", file.getName());

            cloudinary.uploader().deleteByToken(imagem.getAssetId());

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
