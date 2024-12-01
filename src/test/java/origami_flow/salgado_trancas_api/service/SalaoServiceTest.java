package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.SalaoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SalaoServiceTest {

    @InjectMocks
    private SalaoService salaoService;

    @Mock
    private SalaoRepository salaoRepository;

    @Mock
    private EnderecoService enderecoService;

    private Salao mockSalao;
    private Endereco mockEndereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        mockEndereco = new Endereco();
        mockEndereco.setId(1);
        mockEndereco.setCep("12345-678");
        mockEndereco.setLogradouro("Rua Teste");
        mockEndereco.setCidade("Cidade Teste");

        mockSalao = new Salao();
        mockSalao.setId(1);
        mockSalao.setNome("Salão Teste");
        mockSalao.setEndereco(mockEndereco);
    }

    @Test
    void cadastrarSalao_Success() {
        String cep = "12345-678";
        when(enderecoService.cadastrar(cep)).thenReturn(mockEndereco);
        when(salaoRepository.existsByNome("Salão Teste")).thenReturn(false);
        when(salaoRepository.save(mockSalao)).thenReturn(mockSalao);

        Salao salao = salaoService.cadastrarSalao(mockSalao, cep);

        assertNotNull(salao);
        assertEquals("Salão Teste", salao.getNome());
        assertEquals("12345-678", salao.getEndereco().getCep());
        verify(enderecoService, times(1)).cadastrar(cep);
        verify(salaoRepository, times(1)).existsByNome("Salão Teste");
        verify(salaoRepository, times(1)).save(mockSalao);
    }

    @Test
    void cadastrarSalao_NomeDuplicado_ThrowException() {
        String cep = "12345-678";
        when(enderecoService.cadastrar(cep)).thenReturn(mockEndereco);
        when(salaoRepository.existsByNome("Salão Teste")).thenReturn(true);

        EntidadeComConflitoException exception = assertThrows(
                EntidadeComConflitoException.class,
                () -> salaoService.cadastrarSalao(mockSalao, cep)
        );

        assertEquals("Este salão já existe", exception.getMessage());
        verify(enderecoService, times(1)).cadastrar(cep);
        verify(salaoRepository, times(1)).existsByNome("Salão Teste");
        verify(salaoRepository, never()).save(any());
    }

    @Test
    void listar_SaloesExistentes_Success() {
        when(salaoRepository.findAll()).thenReturn(Arrays.asList(mockSalao));

        List<Salao> saloes = salaoService.listar();

        assertNotNull(saloes);
        assertEquals(1, saloes.size());
        verify(salaoRepository, times(1)).findAll();
    }

    @Test
    void listar_NenhumSalaoExistente_EmptyList() {
        when(salaoRepository.findAll()).thenReturn(Arrays.asList());

        List<Salao> saloes = salaoService.listar();

        assertNotNull(saloes);
        assertTrue(saloes.isEmpty());
        verify(salaoRepository, times(1)).findAll();
    }

    @Test
    void salaoPorId_IdExistente_Success() {
        when(salaoRepository.findById(1)).thenReturn(Optional.of(mockSalao));

        Salao salao = salaoService.salaoPorId(1);

        assertNotNull(salao);
        assertEquals("Salão Teste", salao.getNome());
        verify(salaoRepository, times(1)).findById(1);
    }

    @Test
    void salaoPorId_IdInexistente_ThrowException() {
        when(salaoRepository.findById(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> salaoService.salaoPorId(1)
        );

        assertEquals("salão não encontrado (a)", exception.getMessage());
        verify(salaoRepository, times(1)).findById(1);
    }

    @Test
    void atualizarSalao_Success() {
        when(salaoRepository.existsById(1)).thenReturn(true);
        when(salaoRepository.save(mockSalao)).thenReturn(mockSalao);

        Salao salaoAtualizado = salaoService.atualizar(1, mockSalao);

        assertNotNull(salaoAtualizado);
        assertEquals(1, salaoAtualizado.getId());
        assertEquals("Salão Teste", salaoAtualizado.getNome());
        verify(salaoRepository, times(1)).existsById(1);
        verify(salaoRepository, times(1)).save(mockSalao);
    }

    @Test
    void atualizarSalao_IdInexistente_ThrowException() {
        when(salaoRepository.existsById(1)).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> salaoService.atualizar(1, mockSalao)
        );

        assertEquals("salão não encontrado (a)", exception.getMessage());
        verify(salaoRepository, times(1)).existsById(1);
        verify(salaoRepository, never()).save(any());
    }

    @Test
    void atualizarEndereco_IdInexistente_ThrowException() {
        String novoCep = "87654-321";
        when(salaoRepository.findById(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> salaoService.atualizarEndereco(1, novoCep)
        );

        assertEquals("salão não encontrado (a)", exception.getMessage());
        verify(salaoRepository, times(1)).findById(1);
        verify(enderecoService, never()).atualizar(any(), any());
    }

    @Test
    void deletarSalao_IdExistente_Success() {
        when(salaoRepository.existsById(1)).thenReturn(true);
        doNothing().when(salaoRepository).deleteById(1);

        assertDoesNotThrow(() -> salaoService.deletarSalao(1));

        verify(salaoRepository, times(1)).existsById(1);
        verify(salaoRepository, times(1)).deleteById(1);
    }

    @Test
    void deletarSalao_IdInexistente_ThrowException() {
        when(salaoRepository.existsById(1)).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> salaoService.deletarSalao(1)
        );

        assertEquals("salao não encontrado (a)", exception.getMessage());
        verify(salaoRepository, times(1)).existsById(1);
        verify(salaoRepository, never()).deleteById(any());
    }
}