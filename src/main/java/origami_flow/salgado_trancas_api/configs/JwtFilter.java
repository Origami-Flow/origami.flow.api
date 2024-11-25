package origami_flow.salgado_trancas_api.configs;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

import java.io.IOException;
import java.util.ArrayList;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            try {
                HttpStatusCode code = ConexaoApiJwt.validationToke(token).getStatusCode();
                if (code == HttpStatus.UNAUTHORIZED) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }else if (code == HttpStatus.FORBIDDEN) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }else if (code == HttpStatus.OK) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("user", null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }
}
