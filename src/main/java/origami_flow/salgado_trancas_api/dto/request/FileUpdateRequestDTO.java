package origami_flow.salgado_trancas_api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class FileUpdateRequestDTO {

    @JsonProperty("resource_type")
    private MultipartFile file;
}
