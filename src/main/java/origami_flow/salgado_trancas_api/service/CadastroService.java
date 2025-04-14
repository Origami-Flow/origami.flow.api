package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.mapper.CadastroMapper;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final ClienteService clienteService;

    private final TrancistaService trancistaService;

    private final CadastroMapper cadastroMapper;

    public Cliente cadastrarCliente(CadastroRequestDTO cadastroRequestDTO, String cep){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(cadastroRequestDTO.getSenha());
        cadastroRequestDTO.setSenha(senhaCriptografada);
        return clienteService.cadastrarCliente(cadastroMapper.toEntity(cadastroRequestDTO), cep);
    }

    public Trancista cadastrarTrancista(Trancista trancista) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(trancista.getSenha());
        trancista.setSenha(senhaCriptografada);
        return trancistaService.cadastrarTrancista(trancista);
    }
}
