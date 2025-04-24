package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.clients.ViaCepHttpClient;
import origami_flow.salgado_trancas_api.dto.CepDTO;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.CepNaoEncontradoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.EnderecoMapper;
import origami_flow.salgado_trancas_api.repository.EnderecoRepository;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoMapper enderecoMapper;
    private final EnderecoRepository enderecoRepository;
    private final ViaCepHttpClient viaCepHttpClient;

    private CepDTO findByCep(String cep) {
        CepDTO cepEncontrado = viaCepHttpClient.findByCep(cep);
        if (cepEncontrado.getCep() == null) {
            throw new CepNaoEncontradoException("CEP não encontrado");
        }
        return cepEncontrado;
    }

    public Endereco cadastrar(String cep) {
        CepDTO cepEncontrado = findByCep(cep);
        Endereco endereco = enderecoMapper.toEnderecoEntity(cepEncontrado);
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer idEndereco, String cep) {
        if (!enderecoRepository.existsById(idEndereco)) {
            throw new EntidadeNaoEncontradaException("endereço");
        }

        CepDTO cepEncontrado = findByCep(cep);
        Endereco endereco = enderecoMapper.toEnderecoEntity(cepEncontrado);
        endereco.setId(idEndereco);
        return enderecoRepository.save(endereco);
    }
}
