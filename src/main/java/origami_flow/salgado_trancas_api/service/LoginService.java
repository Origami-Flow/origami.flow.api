package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.request.LoginRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.JwtTokenResponse;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.JwtTokenMapper;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final ClienteService clienteService;

    private final TrancistaService trancistaService;

    private final ConexaoApiJwt conexaoApiJwt;

    public JwtTokenResponse autenticarUsuario(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        log.info("Authenticating user with email: [{}]", loginRequestDTO.getEmail());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        UsuarioAbstract usuario;
        log.info("Searching for user with email: [{}]", loginRequestDTO.getEmail());
        usuario = clienteService.buscarPorEmail(loginRequestDTO.getEmail());
        if (usuario == null) usuario = trancistaService.buscarPorEmail(loginRequestDTO.getEmail());
        if (usuario == null) throw new EntidadeNaoEncontradaException("usuário");
        String token = conexaoApiJwt.generateToken(loginRequestDTO);
        return JwtTokenMapper.jwtTokenResponse(usuario, token, authentication.getAuthorities().toString());
    }
}
