package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Endereco;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoService enderecoService;

    private Cliente cliente;
    private Endereco endereco;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente = new Cliente();
        cliente.setId(1);
        cliente.setNome("João Silva");
        cliente.setEmail("joao.silva@example.com");
        cliente.setTelefone("123456789");

        endereco = new Endereco();
        endereco.setId(1);
        endereco.setCep("12345678");
    }

    // 1. Testar listar todos os clientes com sucesso
    @Test
    void listarCliente_Success() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.listarCliente();

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        verify(clienteRepository, times(1)).findAll();
    }

    // 2. Testar listar clientes com lista vazia
    @Test
    void listarCliente_ListaVazia_Success() {
        when(clienteRepository.findAll()).thenReturn(List.of());

        List<Cliente> clientes = clienteService.listarCliente();

        assertNotNull(clientes);
        assertTrue(clientes.isEmpty());
        verify(clienteRepository, times(1)).findAll();
    }

    // 3. Testar buscar cliente por ID com sucesso
    @Test
    void clientePorId_Success() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.clientePorId(1);

        assertNotNull(resultado);
        assertEquals(cliente.getId(), resultado.getId());
        verify(clienteRepository, times(1)).findById(1);
    }

    // 4. Testar buscar cliente por ID inexistente
    @Test
    void clientePorId_IdInexistente_LancaExcecao() {
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.clientePorId(1));
        verify(clienteRepository, times(1)).findById(1);
    }

    // 5. Testar cadastrar cliente com sucesso
    @Test
    void cadastrarCliente_Success() {
        when(clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail())).thenReturn(false);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.cadastrarCliente(cliente);

        assertNotNull(resultado);
        verify(clienteRepository, times(1)).existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail());
        verify(clienteRepository, times(1)).save(cliente);
    }

    // 6. Testar cadastrar cliente com conflito
    @Test
    void cadastrarCliente_Conflito_LancaExcecao() {
        when(clienteRepository.existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail())).thenReturn(true);

        assertThrows(EntidadeComConflitoException.class, () -> clienteService.cadastrarCliente(cliente));
        verify(clienteRepository, times(1)).existsByTelefoneOrEmail(cliente.getTelefone(), cliente.getEmail());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    // 7. Testar atualizar cliente com sucesso
    @Test
    void atualizarCliente_Success() {
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.atualizarCliente(1, cliente);

        assertNotNull(resultado);
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(cliente);
    }

    // 8. Testar atualizar cliente com ID inexistente
    @Test
    void atualizarCliente_IdInexistente_LancaExcecao() {
        when(clienteRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizarCliente(1, cliente));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    // 9. Testar deletar cliente com sucesso
    @Test
    void deletarCliente_Success() {
        when(clienteRepository.existsById(1)).thenReturn(true);

        clienteService.deletarCliente(1);

        verify(clienteRepository, times(1)).existsById(1);
        verify(clienteRepository, times(1)).deleteById(1);
    }

    // 10. Testar deletar cliente com ID inexistente
    @Test
    void deletarCliente_IdInexistente_LancaExcecao() {
        when(clienteRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.deletarCliente(1));
        verify(clienteRepository, times(1)).existsById(1);
        verify(clienteRepository, never()).deleteById(anyInt());
    }

    // 12. Testar cadastrar endereço com cliente inexistente
    @Test
    void cadastrarEndereco_ClienteInexistente_LancaExcecao() {
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.cadastrarEndereco(1, "12345678"));
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    // 14. Testar atualizar endereço com cliente inexistente
    @Test
    void atualizarEndereco_ClienteInexistente_LancaExcecao() {
        when(clienteRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> clienteService.atualizarEndereco(1, "98765432"));
        verify(enderecoService, never()).atualizar(anyInt(), anyString());
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    // 15. Testar buscar clientes por nome com sucesso
    @Test
    void listarPorNome_Success() {
        when(clienteRepository.buscarPorNome("João")).thenReturn(List.of(cliente));

        List<Cliente> clientes = clienteService.listarPorNome("João");

        assertNotNull(clientes);
        assertEquals(1, clientes.size());
        assertEquals("João Silva", clientes.get(0).getNome());
        verify(clienteRepository, times(1)).buscarPorNome("João");
    }

    // 16. Testar buscar clientes por nome com lista vazia
    @Test
    void listarPorNome_NenhumClienteEncontrado() {
        when(clienteRepository.buscarPorNome("João")).thenReturn(List.of());

        List<Cliente> clientes = clienteService.listarPorNome("João");

        assertNotNull(clientes);
        assertTrue(clientes.isEmpty());
        verify(clienteRepository, times(1)).buscarPorNome("João");
    }

    // 17. Testar buscar cliente por email com sucesso
    @Test
    void buscarPorEmail_Success() {
        when(clienteRepository.buscarPorEmail("joao.silva@example.com")).thenReturn(Optional.of(cliente));

        Cliente resultado = clienteService.buscarPorEmail("joao.silva@example.com");

        assertNotNull(resultado);
        assertEquals("joao.silva@example.com", resultado.getEmail());
        verify(clienteRepository, times(1)).buscarPorEmail("joao.silva@example.com");
    }

    // 18. Testar buscar cliente por e-mail não encontrado
    @Test
    void buscarPorEmail_EmailNaoEncontrado() {
        when(clienteRepository.buscarPorEmail("joao.silva@example.com")).thenReturn(Optional.empty());

        Cliente resultado = clienteService.buscarPorEmail("joao.silva@example.com");

        assertNull(resultado);
        verify(clienteRepository, times(1)).buscarPorEmail("joao.silva@example.com");
    }

    // 20. Testar atualizar cliente sem alterar os campos opcionais (nulos)
    @Test
    void atualizarCliente_CamposOpcionaisNaoAlterados_Success() {
        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setNome("Novo Nome");

        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        Cliente resultado = clienteService.atualizarCliente(1, clienteAtualizado);

        assertNotNull(resultado);
        assertEquals("Novo Nome", resultado.getNome());
        verify(clienteRepository, times(1)).findById(1);
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void atualizarCliente_DadosNulos_Success() {
        Cliente atualizado = new Cliente();
        when(clienteRepository.findById(1)).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente resultado = clienteService.atualizarCliente(1, atualizado);

        assertNotNull(resultado);
        assertEquals(cliente.getNome(), resultado.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }
}