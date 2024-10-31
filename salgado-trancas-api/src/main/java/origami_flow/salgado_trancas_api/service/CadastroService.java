package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cadastro.CadastroCriptografadoResponse;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.mapper.CadastroMapper;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;
import origami_flow.salgado_trancas_api.utils.ConexaoApiJwt;

@Service
@RequiredArgsConstructor
public class CadastroService {

    private final ClienteService clienteService;

    private final TrancistaRepository trancistaRepository;

    public Cliente cadastrarCliente(CadastroRequestDTO cadastroRequestDTO, String cep){
        CadastroCriptografadoResponse response = ConexaoApiJwt.criptografarCadastro(cadastroRequestDTO).getBody();
        Cliente cliente = CadastroMapper.toClienteEntity(cadastroRequestDTO, response);
        Cliente clienteCadastrado = clienteService.cadastrarCliente(cliente);
        return clienteService.cadastrarEndereco(clienteCadastrado.getId(), cep);
    }

    public Trancista cadastrarTrancista(Trancista trancista) {
        if (trancistaRepository.existsByEmail(trancista.getEmail())) throw new EntidadeComConflitoException("email");
        trancista.setId(null);
        return trancistaRepository.save(trancista);
    }
}
