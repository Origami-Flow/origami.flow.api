package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

@Service
@RequiredArgsConstructor
public class LoginService {

    public ResponseEntity<JwtTokenResponse> autenticarCliente(LoginRequestDTO loginRequestDTO) {
            return ConexaoApiJwt.loginTokenValidationCliente(loginRequestDTO);
    }

    public ResponseEntity<JwtTokenResponse> autenticarTrancista(LoginRequestDTO loginRequestDTO) {
            return ConexaoApiJwt.loginTokenValidationTrancista(loginRequestDTO);
    }
}
