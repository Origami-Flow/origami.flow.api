package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.EstoqueRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EstoqueServiceTest {

    @InjectMocks
    private EstoqueService estoqueService;

    @Mock
    private EstoqueRepository estoqueRepository;

    private Estoque estoque;
    private Produto produto;
    private Salao salao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = Produto.builder()
                .id(1)
                .nome("Produto Teste")
                .build();

        salao = Salao.builder()
                .id(1)
                .nome("Salão Teste")
                .build();

        estoque = Estoque.builder()
                .id(1)
                .quantidade(10)
                .produto(produto)
                .salao(salao)
                .build();
    }

    @Test
    void cadastrarProdutoNoEstoque_ComSucesso() {
        when(estoqueRepository.save(estoque)).thenReturn(estoque);

        estoqueService.cadastrarProdutoNoEstoque(estoque);

        verify(estoqueRepository, times(1)).save(estoque);
    }

    @Test
    void listarProdutosEmEstoque_ComSucesso() {
        List<Estoque> listaEstoque = List.of(estoque);

        when(estoqueRepository.findAll()).thenReturn(listaEstoque);

        List<Estoque> resultado = estoqueService.listarProdutosEmEstoque();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getProduto().getNome());
        verify(estoqueRepository, times(1)).findAll();
    }

    @Test
    void estoquePorId_Encontrado() {
        when(estoqueRepository.findByProdutoId(produto.getId())).thenReturn(Optional.of(estoque));

        Estoque resultado = estoqueService.estoquePorId(produto.getId());

        assertNotNull(resultado);
        assertEquals("Produto Teste", resultado.getProduto().getNome());
        verify(estoqueRepository, times(1)).findByProdutoId(produto.getId());
    }

    @Test
    void estoquePorId_NaoEncontrado() {
        when(estoqueRepository.findByProdutoId(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> estoqueService.estoquePorId(1)
        );

        assertEquals("produto não encontrado (a)", exception.getMessage());
        verify(estoqueRepository, times(1)).findByProdutoId(1);
    }

    @Test
    void atualizarEstoque_AumentarQuantidade() {
        when(estoqueRepository.findByProdutoId(produto.getId())).thenReturn(Optional.of(estoque));
        when(estoqueRepository.save(any(Estoque.class))).thenReturn(estoque);

        Estoque resultado = estoqueService.atualizarEstoque(produto.getId(), 5);

        assertEquals(15, resultado.getQuantidade());
        verify(estoqueRepository, times(1)).findByProdutoId(produto.getId());
        verify(estoqueRepository, times(1)).save(any(Estoque.class));
    }

    @Test
    void atualizarEstoque_ReduzirQuantidade() {
        when(estoqueRepository.findByProdutoId(produto.getId())).thenReturn(Optional.of(estoque));
        when(estoqueRepository.save(any(Estoque.class))).thenReturn(estoque);

        Estoque resultado = estoqueService.atualizarEstoque(produto.getId(), -5);

        assertEquals(5, resultado.getQuantidade());
        verify(estoqueRepository, times(1)).findByProdutoId(produto.getId());
        verify(estoqueRepository, times(1)).save(any(Estoque.class));
    }

    @Test
    void removerDoEstoque_ComSucesso() {
        when(estoqueRepository.existsById(estoque.getId())).thenReturn(true);

        estoqueService.removerDoEstoque(estoque.getId());

        verify(estoqueRepository, times(1)).existsById(estoque.getId());
        verify(estoqueRepository, times(1)).deleteById(estoque.getId());
    }

    @Test
    void removerDoEstoque_NaoEncontrado() {
        when(estoqueRepository.existsById(estoque.getId())).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> estoqueService.removerDoEstoque(estoque.getId())
        );

        assertEquals("produto não encontrado (a)", exception.getMessage());
        verify(estoqueRepository, times(1)).existsById(estoque.getId());
        verify(estoqueRepository, never()).deleteById(estoque.getId());
    }
}
