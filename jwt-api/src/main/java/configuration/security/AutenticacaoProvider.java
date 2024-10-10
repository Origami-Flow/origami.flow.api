package configuration.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import service.usuario.autenticacao.AutenticacaoService;

public class AutenticacaoProvider implements AuthenticationProvider {
    private final AutenticacaoService usuarioAutorizacaoService ;
    private  AutenticacaoService autenticacaoService;
    private  PasswordEncoder passwordEncoder;

    public AutenticacaoProvider(AutenticacaoService usuarioAutorizacaoService, PasswordEncoder passwordEncoder) {
        this.usuarioAutorizacaoService = usuarioAutorizacaoService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        final String passowd =  authentication.getCredentials().toString();

        UserDetails userDetails = this.usuarioAutorizacaoService.loadUserByUsername(username);

        if (this.passwordEncoder.matches(passowd, userDetails.getPassword())) return  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        throw new BadCredentialsException("Usuário ou Senha inválidos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
