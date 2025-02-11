package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileRequestDTO {

    @NotBlank
    private String name;
    @NotNull
    private MultipartFile file;
    @NotBlank
    private String path;
}