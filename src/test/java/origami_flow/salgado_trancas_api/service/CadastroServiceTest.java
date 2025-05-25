package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Trancista;
import origami_flow.salgado_trancas_api.mapper.CadastroMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CadastroServiceTest {
    @Mock
    private ClienteService clienteService;

    @Mock
    private TrancistaService trancistaService;

    @Mock
    private CadastroMapper cadastroMapper;

    @InjectMocks
    private CadastroService cadastroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Dado que, todos dados para o cadastro do cliente foram inseridos corretamente, deve retornar um cliente cadastrado")
    void cadastrarCliente_Success() {
        CadastroRequestDTO cadastroRequestDTO = new CadastroRequestDTO();
        cadastroRequestDTO.setSenha("senha123");
        cadastroRequestDTO.setEmail("cliente@example.com");

        Cliente clienteMock = new Cliente();
        clienteMock.setId(1);

        when(cadastroMapper.toEntity(cadastroRequestDTO)).thenReturn(clienteMock);
        when(clienteService.cadastrarCliente(clienteMock, "01001000")).thenReturn(clienteMock);

        Cliente clienteCadastrado = cadastroService.cadastrarCliente(cadastroRequestDTO, "01001000");

        assertNotNull(clienteCadastrado);
        assertEquals(1, clienteCadastrado.getId());
        verify(clienteService, times(1)).cadastrarCliente(clienteMock, "01001000");
    }

    @Test
    @DisplayName("Dado que, todos dados para o cadastro da trancista foram inseridos corretamente, deve retornar um(a) trancista cadastrado")
    void cadastrarTrancista_Success() {
        Trancista trancista = new Trancista();
        trancista.setSenha("senha123");

        Trancista trancistaMock = new Trancista();
        trancistaMock.setId(1);

        when(trancistaService.cadastrarTrancista(any(Trancista.class))).thenReturn(trancistaMock);

        Trancista trancistaCadastrado = cadastroService.cadastrarTrancista(trancista);

        assertNotNull(trancistaCadastrado);
        assertEquals(1, trancistaCadastrado.getId());
        verify(trancistaService, times(1)).cadastrarTrancista(any(Trancista.class));
    }

    @Test
    @DisplayName("Dado que, o cliente informou uma senha para cadastro, ela deve ser criptografada ande salvar")
    void senhaCriptografadaAoCadastrarCliente() {
        CadastroRequestDTO cadastroRequestDTO = new CadastroRequestDTO();
        cadastroRequestDTO.setSenha("senha123");

        Cliente clienteMock = new Cliente();
        clienteMock.setId(1);

        when(cadastroMapper.toEntity(cadastroRequestDTO)).thenReturn(clienteMock);
        when(clienteService.cadastrarCliente(clienteMock, "01001000")).thenReturn(clienteMock);

        cadastroService.cadastrarCliente(cadastroRequestDTO, "01001000");

        assertTrue(new BCryptPasswordEncoder().matches("senha123", cadastroRequestDTO.getSenha()));
    }

    @Test
    @DisplayName("Dado que, o cliente informou uma senha para cadastro, ela deve ser criptografada ande salvar")
    void senhaCriptografadaAoCadastrarTrancista() {
        Trancista trancista = new Trancista();
        trancista.setSenha("senha123");

        Trancista trancistaMock = new Trancista();
        trancistaMock.setId(1);

        when(trancistaService.cadastrarTrancista(any(Trancista.class))).thenReturn(trancistaMock);

        cadastroService.cadastrarTrancista(trancista);

        assertTrue(new BCryptPasswordEncoder().matches("senha123", trancista.getSenha()));
    }

    @Test
    void cadastrarTrancista_SenhaVazia_LancaExcecao() {
        Trancista trancista = new Trancista();
        trancista.setSenha(null);

        assertThrows(IllegalArgumentException.class, () -> cadastroService.cadastrarTrancista(trancista));
        verifyNoInteractions(trancistaService);
    }
}