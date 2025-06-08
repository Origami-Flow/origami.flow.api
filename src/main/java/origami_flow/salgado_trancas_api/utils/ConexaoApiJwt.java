package origami_flow.salgado_trancas_api.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;

@Component
@Slf4j
public class ConexaoApiJwt {

    @Value("${ENV_URL_JWT}")
    String apiUrl;

    public String generateToken(LoginRequestDTO loginRequestDTO) {
        String apiUrlGen = apiUrl + "generating-token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequestDTO> request = new HttpEntity<>(loginRequestDTO, headers);
        log.info("Generating token for user: [{}]", loginRequestDTO.getEmail());
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrlGen, request,
              String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        log.error("Failed to generate token for user: [{}], status code: [{}]",
              loginRequestDTO.getEmail(), response.getStatusCode());
        throw new ResponseStatusException(response.getStatusCode());
    }

    public ResponseEntity<String> validationToke(String token) {
        String apiUrlValidate = apiUrl + "validate-token?token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        return restTemplate.postForEntity(apiUrlValidate, request, String.class);
    }
}
