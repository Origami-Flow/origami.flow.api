package service.usuario;

import domain.Usuario;
import domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import mapper.UsuarioMapper;
import org.springframework.stereotype.Service;
import service.usuario.dto.UsuarioCriacaoDto;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto){
        Usuario novoUsuario = usuarioMapper.toUsuarioEntity(usuarioCriacaoDto);
        usuarioRepository.save(novoUsuario);
    }

}
