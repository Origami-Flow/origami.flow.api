package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.constans.FinalidadeProdutoAtendimentoEnum;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.dto.request.ProdutoUtilizadoRequestDTO;
import origami_flow.salgado_trancas_api.entity.*;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.ProdutoUtilizadoMapper;
import origami_flow.salgado_trancas_api.repository.AtendimentoRealizadoRepository;
import origami_flow.salgado_trancas_api.utils.Calculos;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoRealizadoService {

    private final AtendimentoRealizadoRepository atendimentoRealizadoRepository;

    private final ProdutoAtendimentoUtilizadoService produtoAtendimentoUtilizadoService;

    private final ProdutoService produtoService;

    public List<AtendimentoRealizado> listarAtendimentosRealizados(){
        return atendimentoRealizadoRepository.findAll();
    }

    public AtendimentoRealizado cadastrarAtendimentoRealizado(AtendimentoRealizado atendimentoRealizado, Evento evento, List<ProdutoUtilizadoRequestDTO> produtosUtilizadoRequestDTOS){
        List<ProdutoAtendimentoUtilizado> produtosUtilizado = new ArrayList<>();
        if (!produtosUtilizadoRequestDTOS.isEmpty()) {
            for (ProdutoUtilizadoRequestDTO produtosUtilizadoRequestDTO : produtosUtilizadoRequestDTOS) {
                Produto produto = produtoService.produtoPorId(produtosUtilizadoRequestDTO.getId());
                produtosUtilizado.add(ProdutoUtilizadoMapper.toEntity(produtosUtilizadoRequestDTO, produto));
            }
        }
        atendimentoRealizado.setReceita(Calculos.calcularReceita(evento, produtosUtilizado));
        atendimentoRealizado.setEvento(evento);
        AtendimentoRealizado atendimentoSalvo = atendimentoRealizadoRepository.save(atendimentoRealizado);
        produtoAtendimentoUtilizadoService.registrarProdutoUtilizado(produtosUtilizado, atendimentoSalvo);
        return atendimentoSalvo;
    }

    public AtendimentoRealizado atendimentoRealizadoPorId(Integer id){
        return atendimentoRealizadoRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException("atendimento realizado"));
    }

    public AtendimentoRealizado atualizarAtendimento(Integer id,AtendimentoRealizado atendimentoRealizado){
        if (!atendimentoRealizadoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("atendimento realizado");
        atendimentoRealizado.setId(id);
        return atendimentoRealizadoRepository.save(atendimentoRealizado);
    }

    public void apagarAtendimentoRealizado(Integer id){
        if (!atendimentoRealizadoRepository.existsById(id)) throw  new EntidadeNaoEncontradaException("atnedimento realizado");
        atendimentoRealizadoRepository.deleteById(id);
    }
}
