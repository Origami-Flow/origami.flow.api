package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.dto.request.ProdutoUtilizadoRequestDTO;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.EventoRepository;
import origami_flow.salgado_trancas_api.utils.ValidacaoHorario;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository eventoRepository;

    private final ClienteService clienteService;

    private final TrancistaService trancistaService;

    private final ServicoService servicoService;

    private final AuxiliarService auxiliarService;

    private final AtendimentoRealizadoService atendimentoRealizadoService;

    public Evento criarEvento(Evento evento, Integer clienteId, Integer servicoId, Integer trancistaId, Integer auxiliarId) {
        List<Evento> eventos = eventoRepository.findByData(evento.getDataHoraInicio().toLocalDate());
        if (!ValidacaoHorario.validarHorario(eventos, evento)) throw new EntidadeComConflitoException("evento");
        evento.setStatusEvento(StatusEventoEnum.PROGRAMADO);
        evento.setCliente(evento.getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO) ?  clienteService.clientePorId(clienteId) : null);
        evento.setTrancista(evento.getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO) ? trancistaService.trancistaPorId(trancistaId) : null);
        evento.setServico(evento.getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO) ? servicoService.servicoPorId(servicoId) : null);
        evento.setAuxiliar(auxiliarId != null && evento.getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO) ? auxiliarService.auxiliarPorId(auxiliarId) : null);
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento eventoPorId(Integer id) {
        return eventoRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException("evento"));
    }

    public Evento finalizarEvento(Integer id, List<ProdutoUtilizadoRequestDTO> produtosUtilizadoRequestDTO){
        Evento evento = eventoPorId(id);
        if (evento.getStatusEvento() == StatusEventoEnum.FINALIZADO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"evento já finalizado");
        }else {
            evento.setStatusEvento(StatusEventoEnum.FINALIZADO);
            if (evento.getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO)){
                AtendimentoRealizado atendimentoRealizado = new AtendimentoRealizado();
                atendimentoRealizado.setReceita(evento.getServico().getValorServico());
                atendimentoRealizadoService.cadastrarAtendimentoRealizado(atendimentoRealizado, evento, produtosUtilizadoRequestDTO);
            }
        }
        return eventoRepository.save(evento);
    }

    public void apagarEvento (Integer eventoId) {
        if (!eventoRepository.existsById(eventoId)) throw new EntidadeNaoEncontradaException("evento");
        eventoRepository.deleteById(eventoId);
    }

    public List<Evento> listarPorData(LocalDate data) {
        return eventoRepository.findByData(data);
    }

    public Evento atualizarEvento(Evento eventoAtualizado, Integer idEvento, Integer idServico, Integer idTrancista, Integer idAuxiliar) {
        Evento evento = eventoPorId(idEvento);
        if (evento.getStatusEvento().equals(StatusEventoEnum.FINALIZADO)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Evento já finalizado");
        evento.setDataHoraInicio(eventoAtualizado.getDataHoraInicio() != null ? eventoAtualizado.getDataHoraInicio() : evento.getDataHoraInicio());
        evento.setDataHoraTermino(eventoAtualizado.getDataHoraTermino() != null ? eventoAtualizado.getDataHoraTermino() : evento.getDataHoraTermino());
        evento.setTipoEvento(eventoAtualizado.getTipoEvento() != null ? eventoAtualizado.getTipoEvento() : evento.getTipoEvento());
        evento.setServico(idServico != null ? servicoService.servicoPorId(idServico) : evento.getServico());
        evento.setTrancista(idTrancista != null ? trancistaService.trancistaPorId(idTrancista) : evento.getTrancista());
        evento.setAuxiliar(idAuxiliar != null ? auxiliarService.auxiliarPorId(idAuxiliar) : evento.getAuxiliar());
        List<Evento> eventos = eventoRepository.findByData(evento.getDataHoraInicio().toLocalDate());
        if (!ValidacaoHorario.validarHorario(eventos ,evento)) throw new EntidadeComConflitoException("evento");
        return eventoRepository.save(evento);
    }

    public List<Evento> buscarPorData(LocalDate dataInicio, LocalDate dataFim) {
        return eventoRepository.findByData(dataInicio, dataFim);
    }
}