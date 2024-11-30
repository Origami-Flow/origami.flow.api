package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;
import origami_flow.salgado_trancas_api.repository.TrancistaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AutenticacaoServiceTest {

    private ClienteRepository clienteRepository;
    private TrancistaRepository trancistaRepository;
    private AutenticacaoService autenticacaoService;

    @BeforeEach
    void setUp() {
        clienteRepository = mock(ClienteRepository.class);
        trancistaRepository = mock(TrancistaRepository.class);
        autenticacaoService = new AutenticacaoService(trancistaRepository, clienteRepository);
    }

    @Test
    void loadUserByUsername_ClienteEncontrado_DeveRetornarUserDetails() {
        // Mockar cliente encontrado
        String email = "cliente@example.com";
        UserDetails clienteMock = mock(UserDetails.class);
        when(clienteRepository.findByEmail(email)).thenReturn(clienteMock);

        // Executar
        UserDetails resultado = autenticacaoService.loadUserByUsername(email);

        // Verificar
        assertNotNull(resultado, "O UserDetails não deveria ser nulo.");
        assertEquals(clienteMock, resultado, "O UserDetails retornado deveria ser o mesmo do mock.");
        verify(clienteRepository, times(1)).findByEmail(email);
        verify(trancistaRepository, never()).findByEmail(anyString());
    }

    @Test
    void loadUserByUsername_TrancistaEncontrado_DeveRetornarUserDetails() {
        // Mockar cliente não encontrado e trancista encontrado
        String email = "trancista@example.com";
        UserDetails trancistaMock = mock(UserDetails.class);
        when(clienteRepository.findByEmail(email)).thenReturn(null);
        when(trancistaRepository.findByEmail(email)).thenReturn(trancistaMock);

        // Executar
        UserDetails resultado = autenticacaoService.loadUserByUsername(email);

        // Verificar
        assertNotNull(resultado, "O UserDetails não deveria ser nulo.");
        assertEquals(trancistaMock, resultado, "O UserDetails retornado deveria ser o mesmo do mock.");
        verify(clienteRepository, times(1)).findByEmail(email);
        verify(trancistaRepository, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsername_NenhumUsuarioEncontrado_DeveLancarExcecao() {
        // Mockar cliente e trancista não encontrados
        String email = "naoexiste@example.com";
        when(clienteRepository.findByEmail(email)).thenReturn(null);
        when(trancistaRepository.findByEmail(email)).thenReturn(null);

        // Executar e verificar exceção
        UsernameNotFoundException excecao = assertThrows(UsernameNotFoundException.class, () -> {
            autenticacaoService.loadUserByUsername(email);
        });

        assertEquals("Usuario não encontrado", excecao.getMessage());
        verify(clienteRepository, times(1)).findByEmail(email);
        verify(trancistaRepository, times(1)).findByEmail(email);
    }
}