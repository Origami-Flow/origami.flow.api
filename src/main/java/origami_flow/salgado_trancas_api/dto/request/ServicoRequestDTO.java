package origami_flow.salgado_trancas_api.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServicoRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime tempoDuracao;

    @NotNull
    @Positive
    private Double valorServico;

    @NotNull
    @Positive
    private Double valorSinal;

    private MultipartFile imagem;
}
