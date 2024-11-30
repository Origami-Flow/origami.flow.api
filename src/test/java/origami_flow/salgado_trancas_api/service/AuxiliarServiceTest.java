package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Auxiliar;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.AuxiliarRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuxiliarServiceTest {

    @InjectMocks
    private AuxiliarService auxiliarService;

    @Mock
    private AuxiliarRepository auxiliarRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listar_Sucesso() {
        List<Auxiliar> auxiliares = Arrays.asList(
                new Auxiliar(1, "João", "joao@example.com", 500.0),
                new Auxiliar(2, "Maria", "maria@example.com", 600.0)
        );

        when(auxiliarRepository.findAll()).thenReturn(auxiliares);

        List<Auxiliar> resultado = auxiliarService.listar();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(auxiliarRepository, times(1)).findAll();
    }

    @Test
    void auxiliarPorId_Sucesso() {
        Auxiliar auxiliar = new Auxiliar(1, "João", "joao@example.com", 500.0);

        when(auxiliarRepository.findById(1)).thenReturn(Optional.of(auxiliar));

        Auxiliar resultado = auxiliarService.auxiliarPorId(1);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        verify(auxiliarRepository, times(1)).findById(1);
    }

    @Test
    void auxiliarPorId_NaoEncontrado() {
        when(auxiliarRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> auxiliarService.auxiliarPorId(1));
        verify(auxiliarRepository, times(1)).findById(1);
    }

    @Test
    void listarPorNome_Sucesso() {
        List<Auxiliar> auxiliares = Arrays.asList(
                new Auxiliar(1, "João", "joao@example.com", 500.0)
        );

        when(auxiliarRepository.buscarPorNome("João")).thenReturn(auxiliares);

        List<Auxiliar> resultado = auxiliarService.listarPorNome("João");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        verify(auxiliarRepository, times(1)).buscarPorNome("João");
    }

    @Test
    void cadastrar_Sucesso() {
        Auxiliar auxiliar = new Auxiliar(null, "João", "joao@example.com", 500.0);

        when(auxiliarRepository.existsByEmail(auxiliar.getEmail())).thenReturn(false);
        when(auxiliarRepository.save(auxiliar)).thenReturn(new Auxiliar(1, "João", "joao@example.com", 500.0));

        Auxiliar resultado = auxiliarService.cadastrar(auxiliar);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("João", resultado.getNome());
        verify(auxiliarRepository, times(1)).existsByEmail("joao@example.com");
        verify(auxiliarRepository, times(1)).save(auxiliar);
    }

    @Test
    void cadastrar_EmailConflito() {
        Auxiliar auxiliar = new Auxiliar(null, "João", "joao@example.com", 500.0);

        when(auxiliarRepository.existsByEmail(auxiliar.getEmail())).thenReturn(true);

        assertThrows(EntidadeComConflitoException.class, () -> auxiliarService.cadastrar(auxiliar));
        verify(auxiliarRepository, times(1)).existsByEmail("joao@example.com");
    }

    @Test
    void atualizar_Sucesso() {
        Auxiliar auxiliarAtual = new Auxiliar(1, "João", "joao@example.com", 500.0);
        Auxiliar auxiliarAtualizacao = new Auxiliar(null, "João Silva", null, 600.0);

        when(auxiliarRepository.findById(1)).thenReturn(Optional.of(auxiliarAtual));
        when(auxiliarRepository.save(any(Auxiliar.class))).thenReturn(auxiliarAtual);

        Auxiliar resultado = auxiliarService.atualizar(1, auxiliarAtualizacao);

        assertNotNull(resultado);
        assertEquals("João Silva", resultado.getNome());
        assertEquals("joao@example.com", resultado.getEmail());
        assertEquals(600.0, resultado.getValorMaoDeObra());
        verify(auxiliarRepository, times(1)).findById(1);
        verify(auxiliarRepository, times(1)).save(auxiliarAtual);
    }

    @Test
    void atualizar_NaoEncontrado() {
        Auxiliar auxiliarAtualizacao = new Auxiliar(null, "João Silva", null, 600.0);

        when(auxiliarRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> auxiliarService.atualizar(1, auxiliarAtualizacao));
        verify(auxiliarRepository, times(1)).findById(1);
    }

    @Test
    void listar_SemAuxiliares_RetornaListaVazia() {
        when(auxiliarRepository.findAll()).thenReturn(List.of());

        List<Auxiliar> resultado = auxiliarService.listar();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(auxiliarRepository, times(1)).findAll();
    }

    @Test
    void listarPorNome_NomeNaoEncontrado() {
        when(auxiliarRepository.buscarPorNome("Inexistente")).thenReturn(List.of());

        List<Auxiliar> resultado = auxiliarService.listarPorNome("Inexistente");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(auxiliarRepository, times(1)).buscarPorNome("Inexistente");
    }

    @Test
    void atualizar_EmailNaoAlterado() {
        Auxiliar auxiliarAtual = new Auxiliar(1, "João", "joao@example.com", 500.0);
        Auxiliar auxiliarAtualizacao = new Auxiliar(null, "João Atualizado", null, null);

        when(auxiliarRepository.findById(1)).thenReturn(Optional.of(auxiliarAtual));
        when(auxiliarRepository.save(any(Auxiliar.class))).thenReturn(auxiliarAtual);

        Auxiliar resultado = auxiliarService.atualizar(1, auxiliarAtualizacao);

        assertNotNull(resultado);
        assertEquals("João Atualizado", resultado.getNome());
        assertEquals("joao@example.com", resultado.getEmail());
        assertEquals(500.0, resultado.getValorMaoDeObra());
        verify(auxiliarRepository, times(1)).findById(1);
        verify(auxiliarRepository, times(1)).save(auxiliarAtual);
    }

    @Test
    void atualizar_AlteracaoCompleta() {
        Auxiliar auxiliarAtual = new Auxiliar(1, "João", "joao@example.com", 500.0);
        Auxiliar auxiliarAtualizacao = new Auxiliar(null, "João Atualizado", "joao.atualizado@example.com", 700.0);

        when(auxiliarRepository.findById(1)).thenReturn(Optional.of(auxiliarAtual));
        when(auxiliarRepository.save(any(Auxiliar.class))).thenReturn(auxiliarAtual);

        Auxiliar resultado = auxiliarService.atualizar(1, auxiliarAtualizacao);

        assertNotNull(resultado);
        assertEquals("João Atualizado", resultado.getNome());
        assertEquals("joao.atualizado@example.com", resultado.getEmail());
        assertEquals(700.0, resultado.getValorMaoDeObra());
        verify(auxiliarRepository, times(1)).findById(1);
        verify(auxiliarRepository, times(1)).save(auxiliarAtual);
    }
}