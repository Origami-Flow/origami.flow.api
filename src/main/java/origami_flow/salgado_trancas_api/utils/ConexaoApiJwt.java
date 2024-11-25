package origami_flow.salgado_trancas_api.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.dto.response.cadastro.CadastroCriptografadoResponse;

public class ConexaoApiJwt {

    public static String generateToken(LoginRequestDTO loginRequestDTO) {
        String apiUrlGen = "http://localhost:8081/auth/generating-token";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequestDTO> request = new HttpEntity<>(loginRequestDTO, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrlGen, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) return response.getBody();
        throw new ResponseStatusException(response.getStatusCode());
    }

    public static ResponseEntity<String> validationToke(String token) {
        String apiUrl = "http://localhost:8081/auth/validate-token?token=" + token;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        return response;
    }
}
