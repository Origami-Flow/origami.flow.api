package origami_flow.salgado_trancas_api.utils;

import origami_flow.salgado_trancas_api.entity.Evento;
import origami_flow.salgado_trancas_api.entity.Servico;

public class Calculos{

    public static Double calcularReceita(Evento evento){

        return evento.getServico().getValorServico() + evento.getServico().getValorSinal();
    }


}
