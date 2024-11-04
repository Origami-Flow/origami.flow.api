package jwt.api.service.usuario.autenticacao;

import jwt.api.domain.Cliente;
import jwt.api.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import jwt.api.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService {
    private final ClienteRepository clienteRepository;

    // Método da interface implementada
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Cliente> usuarioOpt = clienteRepository.findByEmail(username);

        if (usuarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }

        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}