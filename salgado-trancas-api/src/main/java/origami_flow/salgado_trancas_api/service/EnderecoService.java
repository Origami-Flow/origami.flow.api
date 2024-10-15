package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.EnderecoRepository;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public Endereco cadastrar(Endereco endereco) {
        if (enderecoRepository.existsByNumeroAndComplemento(endereco.getNumero(), endereco.getComplemento())) throw new EntidadeComConflitoException("endereço");
        return enderecoRepository.save(endereco);
    }

    public Endereco atualizar(Integer id, Endereco endereco) {
        if(!enderecoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("endereço");
        endereco.setId(id);
        return  enderecoRepository.save(endereco);
    }
}
