package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.entity.Trancista;
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

    public Evento criarEvento(Evento evento, Integer clienteId, Integer servicoId, Integer trancistaId, Integer auxiliarId) {
        Trancista trancista = trancistaService.trancistaPorId(trancistaId);
        Cliente cliente = clienteService.clientePorId(clienteId);
        Servico servico = servicoService.servicoPorId(servicoId);
        List<Evento> eventos = eventoRepository.findByData(evento.getDataHoraInicio().toLocalDate());
        if (!ValidacaoHorario.validarHorario(eventos, evento)) throw new EntidadeComConflitoException("evento");
        evento.setCliente(cliente);
        evento.setTrancista(trancista);
        evento.setServico(servico);
        evento.setAuxiliar(auxiliarId != null ? auxiliarService.auxiliarPorId(auxiliarId) : null);
        return eventoRepository.save(evento);
    }

    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    public Evento eventoPorId(Integer id) {
        return eventoRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException("evento"));
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
}
