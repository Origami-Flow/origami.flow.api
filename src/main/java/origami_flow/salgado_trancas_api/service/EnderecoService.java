package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.clients.ViaCepHttpClient;
import origami_flow.salgado_trancas_api.dto.CepDTO;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.EnderecoMapper;
import origami_flow.salgado_trancas_api.repository.EnderecoRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnderecoService {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepHttpClient viaCepHttpClient;
    private final CepService cepService;

    public Endereco cadastrar(String cep) {
        CepDTO cepEncontrado = cepService.findByCep(cep);
        Endereco endereco = enderecoMapper.toEnderecoEntity(cepEncontrado);
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer idEndereco, String cep) {
        if (!enderecoRepository.existsById(idEndereco)) {
            throw new EntidadeNaoEncontradaException("endereço");
        }

        CepDTO cepEncontrado = cepService.findByCep(cep);
        Endereco endereco = enderecoMapper.toEnderecoEntity(cepEncontrado);
        endereco.setId(idEndereco);
        return enderecoRepository.save(endereco);
    }
}
