package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import origami_flow.salgado_trancas_api.dto.response.SalaoDetalheResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CaixaRequestDTO {


    private LocalDate dataFechamento;

    private LocalDate dataAbertura;

    private Integer salaoId;

}
