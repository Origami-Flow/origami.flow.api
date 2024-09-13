package origami_flow.salgado_trancas_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Usuario;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarCliente() {
        return usuarioRepository.findAll();
    }

    public Usuario listarClientePorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario atualizarCliente(Integer id, Usuario cliente) {
        if (usuarioRepository.existsById(id)) {
            cliente.setId_usuario(id);
            return usuarioRepository.save(cliente);
        }
        return null;
    }

    public void deletarCliente(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException();
        }
        usuarioRepository.deleteById(id);
    }
}
