package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ServicoRepository;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ServicoServiceTest {

    @InjectMocks
    private ServicoService servicoService;

    @Mock
    private ServicoRepository servicoRepository;

    private Servico mockServico;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockServico = new Servico();
        mockServico.setId(1);
        mockServico.setNome("Trança Box");
        mockServico.setDescricao("Trança box básica");
        mockServico.setTempoDuracao(LocalTime.of(1,20));
        mockServico.setValorServico(200.00);
        mockServico.setValorSinal(50.00);
    }

    @Test
    void listar_ServicosExistentes_Success() {
        when(servicoRepository.findAll()).thenReturn(Arrays.asList(mockServico));

        List<Servico> servicos = servicoService.listar();

        assertNotNull(servicos);
        assertEquals(1, servicos.size());
        verify(servicoRepository, times(1)).findAll();
    }

    @Test
    void listar_NenhumServicoExistente_EmptyList() {
        when(servicoRepository.findAll()).thenReturn(List.of());

        List<Servico> servicos = servicoService.listar();

        assertNotNull(servicos);
        assertTrue(servicos.isEmpty());
        verify(servicoRepository, times(1)).findAll();
    }

    @Test
    void servicoPorId_IdExistente_Success() {
        when(servicoRepository.findById(1)).thenReturn(Optional.of(mockServico));

        Servico servico = servicoService.servicoPorId(1);

        assertNotNull(servico);
        assertEquals("Trança Box", servico.getNome());
        verify(servicoRepository, times(1)).findById(1);
    }

    @Test
    void servicoPorId_IdInexistente_ThrowException() {
        when(servicoRepository.findById(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.servicoPorId(1)
        );

        assertEquals("servico não encontrado (a)", exception.getMessage());
        verify(servicoRepository, times(1)).findById(1);
    }

    @Test
    void criarServico_Success() {
        when(servicoRepository.existsByNome("Trança Box")).thenReturn(false);
        when(servicoRepository.save(mockServico)).thenReturn(mockServico);

        Servico servico = servicoService.criarServico(mockServico, null);

        assertNotNull(servico);
        assertEquals("Trança Box", servico.getNome());
        verify(servicoRepository, times(1)).existsByNome("Trança Box");
        verify(servicoRepository, times(1)).save(mockServico);
    }

    @Test
    void criarServico_NomeDuplicado_ThrowException() {
        when(servicoRepository.existsByNome("Trança Box")).thenReturn(true);

        EntidadeComConflitoException exception = assertThrows(
                EntidadeComConflitoException.class,
                () -> servicoService.criarServico(mockServico, null)
        );

        assertEquals("Este serviço já existe", exception.getMessage());
        verify(servicoRepository, times(1)).existsByNome("Trança Box");
        verify(servicoRepository, never()).save(any());
    }

    @Test
    void atualizarServico_Success() {
        Servico novoServico = new Servico();
        novoServico.setNome("Trança Box Atualizada");
        novoServico.setDescricao(null);
        novoServico.setValorServico(250.00);

        when(servicoRepository.findById(1)).thenReturn(Optional.of(mockServico));
        when(servicoRepository.save(any(Servico.class))).thenReturn(mockServico);

        Servico servicoAtualizado = servicoService.atualizarServico(novoServico, 1, null);

        assertNotNull(servicoAtualizado);
        assertEquals("Trança Box Atualizada", servicoAtualizado.getNome());
        assertEquals("Trança box básica", servicoAtualizado.getDescricao());
        assertEquals(250.00, servicoAtualizado.getValorServico());
        verify(servicoRepository, times(1)).findById(1);
        verify(servicoRepository, times(1)).save(mockServico);
    }

    @Test
    void atualizarServico_IdInexistente_ThrowException() {
        Servico novoServico = new Servico();
        novoServico.setNome("Trança Box Atualizada");

        when(servicoRepository.findById(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.atualizarServico(novoServico, 1, null)
        );

        assertEquals("servico não encontrado (a)", exception.getMessage());
        verify(servicoRepository, times(1)).findById(1);
        verify(servicoRepository, never()).save(any());
    }

    @Test
    void removerServico_IdExistente_Success() {
        when(servicoRepository.existsById(1)).thenReturn(true);
        doNothing().when(servicoRepository).deleteById(1);

        assertDoesNotThrow(() -> servicoService.removerServico(1));

        verify(servicoRepository, times(1)).existsById(1);
        verify(servicoRepository, times(1)).deleteById(1);
    }

    @Test
    void removerServico_IdInexistente_ThrowException() {
        when(servicoRepository.existsById(1)).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> servicoService.removerServico(1)
        );

        assertEquals("servico não encontrado (a)", exception.getMessage());
        verify(servicoRepository, times(1)).existsById(1);
        verify(servicoRepository, never()).deleteById(any());
    }
}