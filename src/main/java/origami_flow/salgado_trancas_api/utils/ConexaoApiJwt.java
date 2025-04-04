package origami_flow.salgado_trancas_api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;

@Component
public class ConexaoApiJwt {

    @Value("${ENV_URL_JWT}")
    String apiUrl;

    public String generateToken(LoginRequestDTO loginRequestDTO) {
        String apiUrlGen = apiUrl + "generating-token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequestDTO> request = new HttpEntity<>(loginRequestDTO, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrlGen, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) return response.getBody();
        throw new ResponseStatusException(response.getStatusCode());
    }

    public ResponseEntity<String> validationToke(String token) {
        String apiUrlValidate = apiUrl + "validate-token?token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrlValidate, request, String.class);
        return response;
    }
}
