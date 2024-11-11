package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Agenda;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.repository.AgendaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {

    private final TrancistaService trancistaService;

    private final AgendaRepository agendaRepository;

    public List<Agenda> listar() {
        return agendaRepository.findAll();
    }

    public List<Agenda> listarPorMes(String mes) {
        return agendaRepository.findByMes(mes);
    }

    public Agenda criarDataAgenda(Agenda agenda, Integer trancistaId) {
        Trancista trancista = trancistaService.trancistaPorId(trancistaId);
        if (agendaRepository.existsByDiaAndAndMesAndAno(agenda.getDia(), agenda.getMes(), agenda.getAno())) throw new EntidadeComConflitoException("data");
        agenda.setTrancista(trancista);
        return agendaRepository.save(agenda);
    }
}
