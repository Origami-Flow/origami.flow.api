package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.clients.ViaCepHttpClient;
import origami_flow.salgado_trancas_api.dto.CepDTO;
import origami_flow.salgado_trancas_api.exceptions.CepNaoEncontradoException;

@Service
@RequiredArgsConstructor
@Slf4j
public class CepService {

    private final ViaCepHttpClient viaCepHttpClient;

    @Cacheable(value = "cepCache", key = "#cep")
    public CepDTO findByCep(String cep) {
        log.info("Buscando CEP: {}", cep);
        CepDTO cepEncontrado = viaCepHttpClient.findByCep(cep);
        if (cepEncontrado.getCep() == null) {
            throw new CepNaoEncontradoException("CEP não encontrado");
        }
        return cepEncontrado;
    }
}