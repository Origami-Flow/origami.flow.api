package origami_flow.salgado_trancas_api.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.dto.response.cadastro.CadastroCriptografadoResponse;

public class ConexaoApiJwt {

    public static ResponseEntity<CadastroCriptografadoResponse> criptografarCadastro(CadastroRequestDTO cadastroRequestDTO) {
//        String apiUrl = System.getenv("api.cadastro");
        String apiUrl = "http://localhost:8081/auth/cadastro";
        RestTemplate  restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CadastroRequestDTO> request = new HttpEntity<>(cadastroRequestDTO, headers);
        ResponseEntity<CadastroCriptografadoResponse> response = restTemplate.postForEntity(apiUrl, request, CadastroCriptografadoResponse.class);
        if (response.getStatusCode() == HttpStatus.CREATED) return response;
        throw new ResponseStatusException(response.getStatusCode());
    }

    public static ResponseEntity<JwtTokenResponse> loginTokenValidationCliente(LoginRequestDTO loginRequestDTO) {
        String apiUrl = "http://localhost:8081/auth/login/cliente";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequestDTO> request = new HttpEntity<>(loginRequestDTO, headers);
        ResponseEntity<JwtTokenResponse> response = restTemplate.postForEntity(apiUrl, request, JwtTokenResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) return response;
        throw new ResponseStatusException(response.getStatusCode());
    }

    public static ResponseEntity<JwtTokenResponse> loginTokenValidationTrancista(LoginRequestDTO loginRequestDTO) {
        String apiUrl = "http://localhost:8081/auth/login/trancista";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequestDTO> request = new HttpEntity<>(loginRequestDTO, headers);
        ResponseEntity<JwtTokenResponse> response = restTemplate.postForEntity(apiUrl, request, JwtTokenResponse.class);
        if (response.getStatusCode() == HttpStatus.OK) return response;
        throw new ResponseStatusException(response.getStatusCode());
    }

//    public static void validationToke(String token) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + token);
//    }
}
