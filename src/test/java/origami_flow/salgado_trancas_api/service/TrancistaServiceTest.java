package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrancistaServiceTest {

    @Mock
    private TrancistaRepository trancistaRepository;

    @InjectMocks
    private TrancistaService trancistaService;

    private Trancista trancista;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trancista = new Trancista();
        trancista.setId(1);
        trancista.setNome("João Silva");
        trancista.setEmail("joao.silva@example.com");
        trancista.setTelefone("123456789");
        trancista.setSenha("senha123");
        trancista.setTipo("ADMIN");
    }

    @Test
    void cadastrarTrancista_Success() {
        when(trancistaRepository.existsByEmailOrTelefone(trancista.getEmail(), trancista.getTelefone()))
                .thenReturn(false);
        when(trancistaRepository.save(trancista)).thenReturn(trancista);

        Trancista resultado = trancistaService.cadastrarTrancista(trancista);

        assertNotNull(resultado);
        assertEquals(trancista.getNome(), resultado.getNome());
        verify(trancistaRepository, times(1)).save(trancista);
    }

    @Test
    void cadastrarTrancista_ConflitoDeEntidade_LancaExcecao() {
        when(trancistaRepository.existsByEmailOrTelefone(trancista.getEmail(), trancista.getTelefone()))
                .thenReturn(true);

        assertThrows(EntidadeComConflitoException.class, () -> trancistaService.cadastrarTrancista(trancista));
        verify(trancistaRepository, never()).save(any(Trancista.class));
    }

    @Test
    void listarTrancistas_Success() {
        when(trancistaRepository.findAll()).thenReturn(List.of(trancista));

        List<Trancista> trancistas = trancistaService.listarTrancista();

        assertNotNull(trancistas);
        assertEquals(1, trancistas.size());
        verify(trancistaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Success() {
        when(trancistaRepository.findById(1)).thenReturn(Optional.of(trancista));

        Trancista resultado = trancistaService.trancistaPorId(1);

        assertNotNull(resultado);
        assertEquals(trancista.getNome(), resultado.getNome());
        verify(trancistaRepository, times(1)).findById(1);
    }

    @Test
    void buscarPorId_Inexistente_LancaExcecao() {
        when(trancistaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> trancistaService.trancistaPorId(1));
        verify(trancistaRepository, times(1)).findById(1);
    }

    @Test
    void atualizarTrancista_Success() {
        Trancista atualizado = new Trancista();
        atualizado.setNome("João Atualizado");

        when(trancistaRepository.findById(1)).thenReturn(Optional.of(trancista));
        when(trancistaRepository.save(any(Trancista.class))).thenReturn(trancista);

        Trancista resultado = trancistaService.atualizarTrancista(1, atualizado);

        assertNotNull(resultado);
        assertEquals("João Atualizado", resultado.getNome());
        verify(trancistaRepository, times(1)).save(any(Trancista.class));
    }

    @Test
    void atualizarTrancista_Inexistente_LancaExcecao() {
        Trancista atualizado = new Trancista();
        atualizado.setNome("João Atualizado");

        when(trancistaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> trancistaService.atualizarTrancista(1, atualizado));
        verify(trancistaRepository, never()).save(any(Trancista.class));
    }

    @Test
    void deletarTrancista_Success() {
        when(trancistaRepository.existsById(1)).thenReturn(true);

        trancistaService.deletar(1);

        verify(trancistaRepository, times(1)).deleteById(1);
    }

    @Test
    void deletarTrancista_Inexistente_LancaExcecao() {
        when(trancistaRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> trancistaService.deletar(1));
        verify(trancistaRepository, never()).deleteById(1);
    }

    @Test
    void buscarPorEmail_Success() {
        when(trancistaRepository.buscarPorEmail(trancista.getEmail()))
                .thenReturn(Optional.of(trancista));

        Trancista resultado = trancistaService.buscarPorEmail(trancista.getEmail());

        assertNotNull(resultado);
        assertEquals(trancista.getEmail(), resultado.getEmail());
        verify(trancistaRepository, times(1)).buscarPorEmail(trancista.getEmail());
    }

    @Test
    void buscarPorEmail_Inexistente_RetornaNull() {
        when(trancistaRepository.buscarPorEmail(trancista.getEmail()))
                .thenReturn(Optional.empty());

        Trancista resultado = trancistaService.buscarPorEmail(trancista.getEmail());

        assertNull(resultado);
        verify(trancistaRepository, times(1)).buscarPorEmail(trancista.getEmail());
    }

    @Test
    void atualizarTrancista_DadosNulos_Success() {
        Trancista atualizado = new Trancista();
        when(trancistaRepository.findById(1)).thenReturn(Optional.of(trancista));
        when(trancistaRepository.save(trancista)).thenReturn(trancista);

        Trancista resultado = trancistaService.atualizarTrancista(1, atualizado);

        assertNotNull(resultado);
        assertEquals(trancista.getNome(), resultado.getNome());
        verify(trancistaRepository, times(1)).save(trancista);
    }

    @Test
    void buscarPorEmail_EmailInvalido_RetornaNull() {
        String emailInvalido = "email-invalido";
        when(trancistaRepository.buscarPorEmail(emailInvalido)).thenReturn(Optional.empty());

        Trancista resultado = trancistaService.buscarPorEmail(emailInvalido);

        assertNull(resultado);
        verify(trancistaRepository, times(1)).buscarPorEmail(emailInvalido);
    }

    @Test
    void listarTrancistas_ListaVazia_Success() {
        when(trancistaRepository.findAll()).thenReturn(List.of());

        List<Trancista> resultado = trancistaService.listarTrancista();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(trancistaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorEmail_NaoCadastrado_RetornaNull() {
        String emailNaoCadastrado = "naoexiste@example.com";
        when(trancistaRepository.buscarPorEmail(emailNaoCadastrado)).thenReturn(Optional.empty());

        Trancista resultado = trancistaService.buscarPorEmail(emailNaoCadastrado);

        assertNull(resultado);
        verify(trancistaRepository, times(1)).buscarPorEmail(emailNaoCadastrado);
    }

    @Test
    void listarTrancistas_MultiplosRegistros_Success() {
        Trancista outroTrancista = new Trancista();
        outroTrancista.setId(2);
        outroTrancista.setNome("Maria Silva");
        outroTrancista.setEmail("maria.silva@example.com");

        when(trancistaRepository.findAll()).thenReturn(List.of(trancista, outroTrancista));


        List<Trancista> resultado = trancistaService.listarTrancista();

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(trancistaRepository, times(1)).findAll();
    }
}