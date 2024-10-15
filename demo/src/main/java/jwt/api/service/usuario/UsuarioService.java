package jwt.api.service.usuario;

import jwt.api.configuration.security.jwt.GerenciadorTokenJwt;
import jwt.api.domain.Usuario;
import jwt.api.domain.repository.UsuarioRepository;
import jwt.api.mapper.UsuarioMapper;
import jwt.api.service.usuario.autenticacao.dto.UsuarioLoginDto;
import jwt.api.service.usuario.autenticacao.dto.UsuarioTokenDto;
import jwt.api.service.usuario.dto.UsuarioCriacaoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario novoUsuario = usuarioMapper.toUsuarioEntity(usuarioCriacaoDto);
        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        usuarioRepository.save(novoUsuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha()
        );
        final Authentication authentication= this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não encontrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return usuarioMapper.toTokenDto(usuarioAutenticado,token);
    }

}
