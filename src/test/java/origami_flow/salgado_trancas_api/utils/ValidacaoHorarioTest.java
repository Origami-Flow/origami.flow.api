package origami_flow.salgado_trancas_api.utils;

import org.junit.jupiter.api.Test;
import origami_flow.salgado_trancas_api.entity.Evento;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ValidacaoHorarioTest {

    @Test
    void validarHorario_SemConflito_DeveRetornarTrue() {
        Evento eventoExistente = Evento.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 8, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 9, 0))
                .build();

        Evento novoEvento = Evento.builder()
                .id(2)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 10, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 11, 0))
                .build();

        boolean resultado = ValidacaoHorario.validarHorario(List.of(eventoExistente), novoEvento);

        assertTrue(resultado, "O horário não deveria apresentar conflito.");
    }

    @Test
    void validarHorario_ComConflito_DeveRetornarFalse() {
        Evento eventoExistente = Evento.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 10, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 12, 0))
                .build();

        Evento novoEvento = Evento.builder()
                .id(2)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 11, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 13, 0))
                .build();

        boolean resultado = ValidacaoHorario.validarHorario(List.of(eventoExistente), novoEvento);

        assertFalse(resultado, "O horário deveria apresentar conflito.");
    }

    @Test
    void validarHorario_MesmoId_SemConflito_DeveRetornarTrue() {
        Evento eventoExistente = Evento.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 10, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 12, 0))
                .build();

        Evento novoEvento = Evento.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 11, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 13, 0))
                .build();

        boolean resultado = ValidacaoHorario.validarHorario(List.of(eventoExistente), novoEvento);

        assertTrue(resultado, "Eventos com o mesmo ID não devem ser tratados como conflito.");
    }

    @Test
    void validarHorario_ConflitoTotal_DeveRetornarFalse() {
        Evento eventoExistente = Evento.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 10, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 12, 0))
                .build();

        Evento novoEvento = Evento.builder()
                .id(2)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 29, 10, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 29, 12, 0))
                .build();

        boolean resultado = ValidacaoHorario.validarHorario(List.of(eventoExistente), novoEvento);

        assertFalse(resultado, "Eventos com intervalos de tempo idênticos devem apresentar conflito.");
    }
}
