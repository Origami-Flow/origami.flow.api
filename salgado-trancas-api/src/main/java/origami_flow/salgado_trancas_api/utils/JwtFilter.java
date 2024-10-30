//package origami_flow.salgado_trancas_api.utils;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        String secretKey = "RXhpc3RlIHVtYSB0ZW9yaWEgcXVlIGRpeiBxdWUsIHNlIHVtIGRpYSBhbGd16W0gZGVzY29icmlyIGV4YXRhbWVudGUgcGFyYSBxdWUgc2VydmUgbyBVbml2ZXJzbyBlIHBvciBxdWUgZWxlIGVzdOEgYXF1aSwgZWxlIGRlc2FwYXJlY2Vy4SBpbnN0YW50YW5lYW1lbnRlIGUgc2Vy4SBzdWJzdGl0de1kbyBwb3IgYWxnbyBhaW5kYSBtYWlzIGVzdHJhbmhvIGUgaW5leHBsaWPhdmVsLiBFeGlzdGUgdW1hIHNlZ3VuZGEgdGVvcmlhIHF1ZSBkaXogcXVlIGlzc28gauEgYWNvbnRlY2V1Li4u";
//        byte[] secret = secretKey.getBytes();
//        String authorizationHeader = request.getHeader("Authorization");
//
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            String token = authorizationHeader.substring(7);
//            try{
//                Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//                request.setAttribute("user", claims);
//            } catch (Exception e) {
//                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                return;
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
