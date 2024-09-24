package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.configuration.security.jwt.GerenciadorTokenJwt;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.entity.UsuarioAbstract;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.dto.autenticacao.UsuarioCriacaoDto;
import origami_flow.salgado_trancas_api.mapper.usuario.UsuarioMapper;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

@Service
public class CadastroService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TrancistaRepository trancistaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;
    @Autowired
    private AuthenticationManager authenticationManager;

    public Cliente cadastrarUsuario(UsuarioCriacaoDto usuarioCriacaoDto){
//        Cliente cliente = new Cliente();
//        cliente.setNome(clienteRequestDTO.nome());
//        cliente.setEmail(clienteRequestDTO.email());
//        cliente.setSenha(clienteRequestDTO.senha());
//        cliente.setTelefone(clienteRequestDTO.telefone());
//        cliente.setGenero(clienteRequestDTO.genero());

        final Cliente cliente = UsuarioMapper.of(usuarioCriacaoDto);

        if (clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail())) throw new EntidadeComConflitoException("telefone ou email ");
        String senhaCriptografada = passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(senhaCriptografada);
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Trancista cadastrarTrancista(Trancista trancista) {
        if (trancistaRepository.existsByEmail(trancista.getEmail())) throw new EntidadeComConflitoException("email");
        trancista.setId(null);
        return trancistaRepository.save(trancista);
    }
}
