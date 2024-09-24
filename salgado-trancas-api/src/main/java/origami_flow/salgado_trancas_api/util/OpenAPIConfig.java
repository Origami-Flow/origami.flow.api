package origami_flow.salgado_trancas_api.util;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.Desktop;
import java.net.URI;

@Configuration
public class OpenAPIConfig {

    // Configuração do Bearer Token no Swagger
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("My API").version("v1"))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components().addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    // Abrir o Swagger no navegador automaticamente ao iniciar a aplicação
    @Bean
    public CommandLineRunner openSwaggerOnStartup() {
        return args -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    URI swaggerUrl = new URI("http://localhost:8080/swagger-ui.html"); // Ajuste conforme necessário
                    Desktop.getDesktop().browse(swaggerUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
