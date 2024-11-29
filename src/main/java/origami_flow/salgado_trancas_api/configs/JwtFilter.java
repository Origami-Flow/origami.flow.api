package origami_flow.salgado_trancas_api.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;
import origami_flow.salgado_trancas_api.service.TrancistaService;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

import java.io.IOException;
import java.util.ArrayList;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private TrancistaRepository trancistaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                ResponseEntity<String> responseToken = ConexaoApiJwt.validationToke(token);
                String login = responseToken.getBody();
                HttpStatusCode code = responseToken.getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }else if (code == HttpStatus.FORBIDDEN) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }else if (code == HttpStatus.OK) {
                    UserDetails user = getUserDetails(login);
                    response.setStatus(HttpServletResponse.SC_OK);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }

    private UserDetails getUserDetails(String login) {
        UserDetails user;
        user = clienteRepository.findByEmail(login);
        if (user == null) {
            user = trancistaRepository.findByEmail(login);
        }
        return user;
    }
}
