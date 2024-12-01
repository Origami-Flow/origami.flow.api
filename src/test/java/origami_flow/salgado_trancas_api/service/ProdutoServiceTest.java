package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.exceptions.RequisicaoErradaException;
import origami_flow.salgado_trancas_api.repository.ProdutoRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private EstoqueService estoqueService;

    @Mock
    private SalaoService salaoService;

    private Produto produto;
    private Salao salao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        produto = Produto.builder()
                .id(1)
                .nome("Produto Teste")
                .valorCompra(50.0)
                .valorVenda(100.0)
                .build();

        salao = Salao.builder()
                .id(1)
                .nome("Salão Teste")
                .build();
    }

    @Test
    void listarTodosProdutos_ComSucesso() {
        List<Produto> produtos = List.of(produto);

        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> resultado = produtoService.listarTodosProdutos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getNome());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void produtoPorId_Encontrado() {
        when(produtoRepository.findById(1)).thenReturn(Optional.of(produto));

        Produto resultado = produtoService.produtoPorId(1);

        assertNotNull(resultado);
        assertEquals("Produto Teste", resultado.getNome());
        verify(produtoRepository, times(1)).findById(1);
    }

    @Test
    void produtoPorId_NaoEncontrado() {
        when(produtoRepository.findById(1)).thenReturn(Optional.empty());

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> produtoService.produtoPorId(1)
        );

        assertEquals("produto não encontrado (a)", exception.getMessage());
        verify(produtoRepository, times(1)).findById(1);
    }

    @Test
    void cadastrarProduto_ComSucesso() {
        Estoque estoque = Estoque.builder()
                .quantidade(10)
                .produto(produto)
                .salao(salao)
                .build();

        when(salaoService.salaoPorId(1)).thenReturn(salao);
        when(produtoRepository.existsByNome(produto.getNome())).thenReturn(false);
        when(produtoRepository.save(produto)).thenReturn(produto);
        doNothing().when(estoqueService).cadastrarProdutoNoEstoque(estoque);

        Produto resultado = produtoService.cadastrarProduto(produto, 1, 10);

        assertNotNull(resultado);
        assertEquals("Produto Teste", resultado.getNome());
        verify(produtoRepository, times(1)).existsByNome(produto.getNome());
        verify(produtoRepository, times(1)).save(produto);
        verify(estoqueService, times(1)).cadastrarProdutoNoEstoque(any(Estoque.class));
    }

    @Test
    void cadastrarProduto_ConflitoDeNome() {
        when(salaoService.salaoPorId(1)).thenReturn(salao);
        when(produtoRepository.existsByNome(produto.getNome())).thenReturn(true);

        EntidadeComConflitoException exception = assertThrows(
                EntidadeComConflitoException.class,
                () -> produtoService.cadastrarProduto(produto, 1, 10)
        );

        assertEquals("Este produto já existe", exception.getMessage());
        verify(produtoRepository, times(1)).existsByNome(produto.getNome());
        verify(produtoRepository, never()).save(any(Produto.class));
        verify(estoqueService, never()).cadastrarProdutoNoEstoque(any(Estoque.class));
    }

    @Test
    void atualizarProduto_ComSucesso() {
        when(produtoRepository.existsById(1)).thenReturn(true);
        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto resultado = produtoService.atualizarProduto(1, produto);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Produto Teste", resultado.getNome());
        verify(produtoRepository, times(1)).existsById(1);
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void atualizarProduto_NaoEncontrado() {
        when(produtoRepository.existsById(1)).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> produtoService.atualizarProduto(1, produto)
        );

        assertEquals("produto não encontrado (a)", exception.getMessage());
        verify(produtoRepository, times(1)).existsById(1);
        verify(produtoRepository, never()).save(any(Produto.class));
    }

    @Test
    void deletarProduto_ComSucesso() {
        when(produtoRepository.existsById(1)).thenReturn(true);
        doNothing().when(produtoRepository).deleteById(1);

        produtoService.deletarProduto(1);

        verify(produtoRepository, times(1)).existsById(1);
        verify(produtoRepository, times(1)).deleteById(1);
    }

    @Test
    void deletarProduto_NaoEncontrado() {
        when(produtoRepository.existsById(1)).thenReturn(false);

        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> produtoService.deletarProduto(1)
        );

        assertEquals("produto não encontrado (a)", exception.getMessage());
        verify(produtoRepository, times(1)).existsById(1);
        verify(produtoRepository, never()).deleteById(1);
    }

    @Test
    void buscarProdutoNome_ComSucesso() {
        List<Produto> produtos = List.of(produto);

        when(produtoRepository.findAllByOrderByNome("Produto Teste")).thenReturn(produtos);

        List<Produto> resultado = produtoService.buscarProdutoNome("Produto Teste");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getNome());
        verify(produtoRepository, times(1)).findAllByOrderByNome("Produto Teste");
    }

    @Test
    void listarTodosPorId_ComSucesso() {
        List<Produto> produtos = List.of(produto);

        when(produtoRepository.findAllById(List.of(1))).thenReturn(produtos);

        List<Produto> resultado = produtoService.listarTodosPorId(List.of(1));

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Produto Teste", resultado.get(0).getNome());
        verify(produtoRepository, times(1)).findAllById(List.of(1));
    }

    @Test
    void cadastrarProduto_QuantidadeInvalida() {
        Salao salao = Salao.builder().id(1).nome("Salão Teste").build();
        Produto novoProduto = Produto.builder()
                .id(2)
                .nome("Produto Novo")
                .valorCompra(30.0)
                .valorVenda(70.0)
                .build();

        when(salaoService.salaoPorId(1)).thenReturn(salao);
        when(produtoRepository.existsByNome("Produto Novo")).thenReturn(false);

        RequisicaoErradaException exception = assertThrows(
                RequisicaoErradaException.class,
                () -> produtoService.cadastrarProduto(novoProduto, 1, -5)
        );

        assertEquals("Quantidade de produtos inválida", exception.getMessage());
         verify(produtoRepository, never()).save(any(Produto.class));
        verify(estoqueService, never()).cadastrarProdutoNoEstoque(any(Estoque.class));
    }

    @Test
    void listarTodosProdutos_Vazio() {
        when(produtoRepository.findAll()).thenReturn(List.of());

        List<Produto> resultado = produtoService.listarTodosProdutos();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    void buscarProdutoNome_Inexistente() {
        when(produtoRepository.findAllByOrderByNome("Produto Inexistente")).thenReturn(List.of());

        List<Produto> resultado = produtoService.buscarProdutoNome("Produto Inexistente");

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(produtoRepository, times(1)).findAllByOrderByNome("Produto Inexistente");
    }

    @Test
    void atualizarProduto_SemMudancas() {
        Produto produtoAtual = Produto.builder()
                .id(1)
                .nome("Produto Atual")
                .valorCompra(50.0)
                .valorVenda(100.0)
                .build();

        when(produtoRepository.existsById(1)).thenReturn(true);
        when(produtoRepository.save(produtoAtual)).thenReturn(produtoAtual);

        Produto resultado = produtoService.atualizarProduto(1, produtoAtual);

        assertNotNull(resultado);
        assertEquals("Produto Atual", resultado.getNome());
        assertEquals(100.0, resultado.getValorVenda());
        verify(produtoRepository, times(1)).existsById(1);
        verify(produtoRepository, times(1)).save(produtoAtual);
    }

    @Test
    void listarTodosPorId_IdsInexistentes() {
        when(produtoRepository.findAllById(List.of(99, 100))).thenReturn(List.of());

        List<Produto> resultado = produtoService.listarTodosPorId(List.of(99, 100));

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(produtoRepository, times(1)).findAllById(List.of(99, 100));
    }

    @Test
    void deletarProduto_ComProdutoAssociadoAoEstoque() {
        when(produtoRepository.existsById(1)).thenReturn(true);
        doThrow(new IllegalStateException("Produto está associado ao estoque e não pode ser deletado"))
                .when(produtoRepository)
                .deleteById(1);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> produtoService.deletarProduto(1)
        );

        assertEquals("Produto está associado ao estoque e não pode ser deletado", exception.getMessage());
        verify(produtoRepository, times(1)).existsById(1);
        verify(produtoRepository, times(1)).deleteById(1);
    }

}
