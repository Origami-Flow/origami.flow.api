package origami_flow.salgado_trancas_api.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenResponse {

    @Schema(description = "Identificador único do cliente", example = "1")
    private Integer id;

    @Schema(description = "Nome do cliente", example = "Jean Santos")
    private String nome;

    @Schema(description = "Email do cliente", example = "jean.santos@example.com")
    private String email;

    @Schema(description = "Token JWT gerado para autenticação", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}
