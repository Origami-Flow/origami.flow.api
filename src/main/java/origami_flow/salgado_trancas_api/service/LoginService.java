package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.mapper.JwtTokenMapper;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final ClienteService clienteService;
    private final TrancistaService trancistaService;

    public JwtTokenResponse autenticarCliente(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        Cliente cliente = clienteService.buscarPorEmail(loginRequestDTO.getEmail());
        String token = ConexaoApiJwt.generateToken(loginRequestDTO);
        return JwtTokenMapper.jwtTokenResponse(cliente, token);
    }

    public JwtTokenResponse autenticarTrancista(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        Trancista trancista = trancistaService.buscarPorEmail(loginRequestDTO.getEmail());
        String token = ConexaoApiJwt.generateToken(loginRequestDTO);
        return JwtTokenMapper.jwtTokenResponse(trancista, token);
    }
}
