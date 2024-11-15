package origami_flow.salgado_trancas_api.utils;

import origami_flow.salgado_trancas_api.entity.Evento;

import java.util.List;

public class ValidacaoHorario {

    public static boolean validarHorario(List<Evento> eventos, Evento novoEvento) {
        for (Evento evento : eventos) {
            if(!evento.getId().equals(novoEvento.getId())) {
                if(
                        (evento.getDataHoraInicio().withNano(0).isAfter(novoEvento.getDataHoraInicio().withNano(0)) && evento.getDataHoraInicio().withNano(0).isBefore(novoEvento.getDataHoraTermino().withNano(0)))||
                        (evento.getDataHoraInicio().withNano(0).isAfter(novoEvento.getDataHoraInicio().withNano(0)) && evento.getDataHoraTermino().withNano(0).isBefore(novoEvento.getDataHoraTermino().withNano(0)))||
                        (evento.getDataHoraInicio().withNano(0).isBefore(novoEvento.getDataHoraInicio().withNano(0)) && evento.getDataHoraTermino().withNano(0).isAfter(novoEvento.getDataHoraTermino().withNano(0)))||
                        (evento.getDataHoraInicio().withNano(0).isBefore(novoEvento.getDataHoraInicio().withNano(0)) && evento.getDataHoraTermino().withNano(0).isAfter(novoEvento.getDataHoraInicio().withNano(0)))||
                        (evento.getDataHoraInicio().withNano(0).isEqual(novoEvento.getDataHoraInicio().withNano(0)) && evento.getDataHoraTermino().withNano(0).isEqual(novoEvento.getDataHoraTermino().withNano(0)))
                ){
                    return false;
                }
            }
        }
       return true;
    }
}
