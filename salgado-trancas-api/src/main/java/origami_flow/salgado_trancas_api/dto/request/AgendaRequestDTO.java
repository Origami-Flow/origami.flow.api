package origami_flow.salgado_trancas_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendaRequestDTO {

    @NotNull
    @Positive
    private Integer dia;

    @NotBlank
    private String mes;

    @NotNull
    @Positive
    private Integer ano;

    @NotNull
    private Integer trancistaId;
}
