package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarCliente() {
        return usuarioRepository.findAll();
    }

    public Usuario clientePorId(Integer id) {
        Optional<Usuario> opt = usuarioRepository.findById(id);
        if (opt.isEmpty()) throw new EntidadeNaoEncontradaException("Usuario ");

        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario atualizarCliente(Integer id, Usuario cliente) {
        if (usuarioRepository.existsById(id)) {
            cliente.setId_usuario(id);
            return usuarioRepository.save(cliente);
        }
        throw new EntidadeNaoEncontradaException("Usuario ");
    }

    public void deletarCliente(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Usuario ");
        }
        usuarioRepository.deleteById(id);
    }
}
