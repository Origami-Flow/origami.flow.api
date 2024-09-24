package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.configuration.security.jwt.GerenciadorTokenJwt;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioLoginDto;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioTokenDto;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.usuario.UsuarioMapper;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
public class LoginService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TrancistaRepository trancistaRepository;
    @Autowired
    GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    AuthenticationManager authenticationManager;

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());
        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        UsuarioAbstract usuarioAutenticado =
                clienteRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new EntidadeNaoEncontradaException("Email do usuário não cadastrado")
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);


        /*UsuarioAbstract usuarioEncontrado = clienteRepository.findByEmailAndSenha(email,senha);
        if (usuarioEncontrado != null) {
            return usuarioEncontrado;
        }
        usuarioEncontrado = trancistaRepository.findByEmailAndSenha(email, senha);
        if (usuarioEncontrado !=null) {
            return usuarioEncontrado;
        }
        throw new EntidadeNaoEncontradaException("Usuario");*/
    }
}
