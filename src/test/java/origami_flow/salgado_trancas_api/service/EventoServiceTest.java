package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import origami_flow.salgado_trancas_api.constans.StatusEventoEnum;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.entity.*;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.EventoRepository;
import origami_flow.salgado_trancas_api.utils.ValidacaoHorario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventoServiceTest {

    @InjectMocks
    private EventoService eventoService;

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private TrancistaService trancistaService;

    @Mock
    private ServicoService servicoService;

    @Mock
    private AuxiliarService auxiliarService;

    @Mock
    private AtendimentoRealizadoService atendimentoRealizadoService;

    @Mock
    private ValidacaoHorario validacaoHorario;

    private Evento eventoBase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        eventoBase = Evento.builder()
                .id(1)
                .dataHoraInicio(LocalDateTime.of(2024, 11, 30, 10, 0))
                .dataHoraTermino(LocalDateTime.of(2024, 11, 30, 11, 0))
                .tipoEvento(TipoEventoEnum.ATENDIMENTO)
                .statusEvento(StatusEventoEnum.PROGRAMADO)
                .servico(new Servico())
                .cliente(new Cliente())
                .auxiliar(new Auxiliar())
                .trancista(new Trancista())
                .build();
    }

    @Test
    void finalizarEvento_Sucesso() {
        eventoBase.setStatusEvento(StatusEventoEnum.PROGRAMADO);
        when(eventoRepository.findById(1)).thenReturn(Optional.of(eventoBase));
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoBase);

        Evento resultado = eventoService.finalizarEvento(1, List.of());

        assertNotNull(resultado);
        assertEquals(StatusEventoEnum.FINALIZADO, resultado.getStatusEvento());
        verify(atendimentoRealizadoService, times(1)).cadastrarAtendimentoRealizado(any(), eq(eventoBase), any());
        verify(eventoRepository, times(1)).save(eventoBase);
    }

    @Test
    void finalizarEvento_JaFinalizado() {
        eventoBase.setStatusEvento(StatusEventoEnum.FINALIZADO);
        when(eventoRepository.findById(1)).thenReturn(Optional.of(eventoBase));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> eventoService.finalizarEvento(1, List.of()));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("evento já finalizado", exception.getReason());
        verify(eventoRepository, never()).save(any());
    }

    @Test
    void atualizarEvento_Sucesso() {
        when(eventoRepository.findById(1)).thenReturn(Optional.of(eventoBase));
        when(servicoService.servicoPorId(1)).thenReturn(new Servico());
        when(trancistaService.trancistaPorId(1)).thenReturn(new Trancista());
        when(eventoRepository.save(any(Evento.class))).thenReturn(eventoBase);

        Evento atualizado = eventoService.atualizarEvento(eventoBase, 1, 1, 1, null);

        assertNotNull(atualizado);
        assertEquals(eventoBase.getDataHoraInicio(), atualizado.getDataHoraInicio());
        verify(eventoRepository, times(1)).save(eventoBase);
    }

    @Test
    void atualizarEvento_Finalizado() {
        eventoBase.setStatusEvento(StatusEventoEnum.FINALIZADO);
        when(eventoRepository.findById(1)).thenReturn(Optional.of(eventoBase));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> eventoService.atualizarEvento(eventoBase, 1, 1, 1, null));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        assertEquals("Evento já finalizado", exception.getReason());
        verify(eventoRepository, never()).save(any());
    }

    @Test
    void apagarEvento_Sucesso() {
        when(eventoRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> eventoService.apagarEvento(1));
        verify(eventoRepository, times(1)).deleteById(1);
    }

    @Test
    void apagarEvento_NaoEncontrado() {
        when(eventoRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> eventoService.apagarEvento(1));
        verify(eventoRepository, never()).deleteById(any());
    }

    @Test
    void listarPorData_Sucesso() {
        when(eventoRepository.findByData(any())).thenReturn(List.of(eventoBase));

        List<Evento> eventos = eventoService.listarPorData(LocalDate.of(2024, 11, 30));

        assertNotNull(eventos);
        assertFalse(eventos.isEmpty());
        verify(eventoRepository, times(1)).findByData(any());
    }
}