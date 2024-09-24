package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioDetalhesDto;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

import java.util.Optional;

@Service

public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TrancistaRepository trancistaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> clienteOptional = clienteRepository.findByEmail(username);

        if (!clienteOptional.isEmpty()) return new UsuarioDetalhesDto(clienteOptional.get());

        Optional<Trancista> trancistaOptional = trancistaRepository.findByEmail(username);

        if (!trancistaOptional.isEmpty())   return new UsuarioDetalhesDto(trancistaOptional.get());

        throw new  EntidadeNaoEncontradaException("usuario");
    }
}
