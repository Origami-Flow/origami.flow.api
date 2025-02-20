package origami_flow.salgado_trancas_api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import origami_flow.salgado_trancas_api.dto.CepDTO;

@FeignClient(name = "ViaCepHttpClient", url = "viacep.com.br/ws/")
public interface ViaCepHttpClient {
    @GetMapping("{cep}/json")
    CepDTO findByCep(@PathVariable String cep);
}
