package origami_flow.salgado_trancas_api.service;

import com.gtbr.ViaCepClient;
import com.gtbr.domain.Cep;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.EnderecoMapper;
import origami_flow.salgado_trancas_api.repository.EnderecoRepository;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoMapper enderecoMapper;

    private final EnderecoRepository enderecoRepository;

    public Endereco cadastrar(String cep) {
        Cep cepEncontrado = ViaCepClient.findCep(cep);
        if (cepEncontrado.getCep() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Endereco endereco = enderecoMapper.toEnderecoEntity(cepEncontrado);
        if (enderecoRepository.existsByNumeroAndComplemento(endereco.getNumero(), endereco.getComplemento())) throw new EntidadeComConflitoException("endereço");
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer idEndereco, String cep) {
        if(!enderecoRepository.existsById(idEndereco)) throw new EntidadeNaoEncontradaException("endereço");
        Cep cepEncontrado = ViaCepClient.findCep(cep);
        if (cepEncontrado.getCep() == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        Endereco endereco = enderecoMapper.toEnderecoEntity(cepEncontrado);
        endereco.setId(idEndereco);
        return  enderecoRepository.save(endereco);
    }
}
