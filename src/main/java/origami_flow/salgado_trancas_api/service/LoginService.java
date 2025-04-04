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
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.JwtTokenMapper;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;

    private final ClienteService clienteService;

    private final TrancistaService trancistaService;

    private final ConexaoApiJwt conexaoApiJwt;

    public JwtTokenResponse autenticarUsuario(LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);
        UsuarioAbstract usuario;
        usuario = clienteService.buscarPorEmail(loginRequestDTO.getEmail());
        if (usuario == null) usuario = trancistaService.buscarPorEmail(loginRequestDTO.getEmail());
        if (usuario == null) throw new EntidadeNaoEncontradaException("usuário");
        String token = conexaoApiJwt.generateToken(loginRequestDTO);
        return JwtTokenMapper.jwtTokenResponse(usuario, token, authentication.getAuthorities().toString());
    }
}
