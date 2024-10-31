package jwt.api.service.usuario;

import jwt.api.configuration.security.jwt.GerenciadorTokenJwt;
import jwt.api.domain.Cliente;
import jwt.api.domain.Trancista;
import jwt.api.domain.repository.ClienteRepository;
import jwt.api.domain.repository.TrancistaRepository;
import jwt.api.service.usuario.autenticacao.dto.CadastroCriptografado;
import jwt.api.service.usuario.autenticacao.dto.LoginRequestDTO;
import jwt.api.service.usuario.autenticacao.dto.JwtTokenResponse;
import jwt.api.service.usuario.autenticacao.dto.CadastroRequestDTO;
import jwt.api.service.usuario.mapper.CadastroCriptografadoMapper;
import jwt.api.service.usuario.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Service
public class AutenticationService {


    private final PasswordEncoder passwordEncoder;

    private final ClienteRepository clienteRepository;

    private final TrancistaRepository trancistaRepository;

    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    private final AuthenticationManager authenticationManager;

    public CadastroCriptografado criar(CadastroRequestDTO cadastroRequestDTO) {
        CadastroCriptografado criptografado = CadastroCriptografadoMapper.of(cadastroRequestDTO);
        String senhaCriptografada = passwordEncoder.encode(cadastroRequestDTO.getSenha());
        criptografado.setSenha(senhaCriptografada);
        return criptografado;
    }

    public JwtTokenResponse autenticarCliente(LoginRequestDTO loginRequestDTO) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);
        Cliente clienteAutenticado = clienteRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(clienteAutenticado, token);
    }

    public JwtTokenResponse autenticarTrancista(LoginRequestDTO loginRequestDTO) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getSenha());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);
        Trancista trancistaAutenticado = trancistaRepository.findByEmail(loginRequestDTO.getEmail()).orElseThrow(() -> new ResponseStatusException(404, "Email do usuário não cadastrado", null));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);
        return UsuarioMapper.of(trancistaAutenticado, token);
    }
}