package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.AtendimentoRealizadoRepository;
import origami_flow.salgado_trancas_api.utils.Calculos;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoRealizadoService {

    private final AtendimentoRealizadoRepository atendimentoRealizadoRepository;

    public List<AtendimentoRealizado> listarAtendimentosRealizados(){
        return atendimentoRealizadoRepository.findAll();
    }

    public AtendimentoRealizado cadastrarAtendimentoRealizado(AtendimentoRealizado atendimentoRealizado, Evento evento){
        if (atendimentoRealizado.getReceita() >= 0) throw  new EntidadeComConflitoException("atendimento realizado");

        if (evento.getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO))
        atendimentoRealizado.setReceita(Calculos.calcularReceita(evento));

        atendimentoRealizado.setEvento(evento);
        return atendimentoRealizadoRepository.save(atendimentoRealizado);
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
