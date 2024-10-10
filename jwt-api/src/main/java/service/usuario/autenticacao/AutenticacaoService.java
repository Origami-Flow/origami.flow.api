package service.usuario.autenticacao;

import domain.Usuario;
import domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import service.usuario.autenticacao.dto.UsuarioDetalhesDto;

import java.util.Optional;

@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional= usuarioRepository.findByEmail(username);

        if (usuarioOptional.isEmpty()) throw  new UsernameNotFoundException(String.format("usuario: %s não encontrado", username));
        return new UsuarioDetalhesDto(usuarioOptional.get().getNome(),usuarioOptional.get().getEmail(),usuarioOptional.get().getSenha());
    }


}
