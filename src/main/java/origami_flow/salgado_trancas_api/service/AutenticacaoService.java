package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
@RequiredArgsConstructor
public class AutenticacaoService implements UserDetailsService {

    private final TrancistaRepository trancistaRepository;

    private final ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = clienteRepository.findByEmail(username);
        if (user == null) {
            user = trancistaRepository.findByEmail(username);
        }
        return user;
    }
}
