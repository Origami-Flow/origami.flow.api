package origami_flow.salgado_trancas_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SalgadoTrancasApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalgadoTrancasApiApplication.class, args);
	}

}
