package origami_flow.salgado_trancas_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import origami_flow.salgado_trancas_api.entity.Caixa;
import origami_flow.salgado_trancas_api.entity.Despesa;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.DespesaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DespesaServiceTest {

    @InjectMocks
    private DespesaService despesaService;

    @Mock
    private DespesaRepository despesaRepository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private CaixaService caixaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarTodasDepespesas_Sucesso() {
        when(despesaRepository.findAll()).thenReturn(List.of(new Despesa(), new Despesa()));

        List<Despesa> despesas = despesaService.listarTodasDepespesas();

        assertNotNull(despesas);
        assertEquals(2, despesas.size());
        verify(despesaRepository, times(1)).findAll();
    }

    @Test
    void despesaPorId_Sucesso() {
        Despesa despesa = Despesa.builder().id(1).build();
        when(despesaRepository.findById(1)).thenReturn(Optional.of(despesa));

        Despesa resultado = despesaService.despesaPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        verify(despesaRepository, times(1)).findById(1);
    }

    @Test
    void despesaPorId_EntidadeNaoEncontrada() {
        when(despesaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> despesaService.despesaPorId(1));
        verify(despesaRepository, times(1)).findById(1);
    }

    @Test
    void cadastrarDespesa_ComProduto_Sucesso() {
        Produto produto = Produto.builder().id(1).valorCompra(100.0).build();
        Caixa caixa = Caixa.builder().id(1).despesaTotal(200.0).build();
        Despesa despesa = Despesa.builder().descricao("Compra de produto").build();

        when(produtoService.produtoPorId(1)).thenReturn(produto);
        when(caixaService.caixaPorId(1)).thenReturn(caixa);
        when(despesaRepository.save(any(Despesa.class))).thenReturn(despesa);

        Despesa resultado = despesaService.cadastrarDespesa(despesa, 1, 1);

        assertNotNull(resultado);
        assertEquals(produto, resultado.getProduto());
        assertEquals(caixa, resultado.getCaixa());
        assertEquals(300.0, caixa.getDespesaTotal());
        verify(produtoService, times(1)).produtoPorId(1);
        verify(caixaService, times(1)).caixaPorId(1);
        verify(despesaRepository, times(1)).save(despesa);
    }

    @Test
    void cadastrarDespesa_SemProduto_Sucesso() {
        Caixa caixa = Caixa.builder().id(1).despesaTotal(100.0).build();
        Despesa despesa = Despesa.builder().descricao("Despesa de operação").valor(50.0).build();

        when(caixaService.caixaPorId(1)).thenReturn(caixa);
        when(despesaRepository.save(any(Despesa.class))).thenReturn(despesa);

        Despesa resultado = despesaService.cadastrarDespesa(despesa, null, 1);

        assertNotNull(resultado);
        assertNull(resultado.getProduto());
        assertEquals(caixa, resultado.getCaixa());
        assertEquals(150.0, caixa.getDespesaTotal());
        verify(caixaService, times(1)).caixaPorId(1);
        verify(despesaRepository, times(1)).save(despesa);
    }

    @Test
    void atualizarDespesa_Sucesso() {
        Produto produto = Produto.builder().id(1).build();
        Caixa caixa = Caixa.builder().id(1).build();
        Despesa despesaExistente = Despesa.builder().id(1).descricao("Antiga despesa").build();
        Despesa despesaAtualizada = Despesa.builder()
                .descricao("Nova despesa")
                .produto(produto)
                .caixa(caixa)
                .data(LocalDate.now())
                .build();

        when(despesaRepository.findById(1)).thenReturn(Optional.of(despesaExistente));
        when(produtoService.produtoPorId(1)).thenReturn(produto);
        when(caixaService.caixaPorId(1)).thenReturn(caixa);
        when(despesaRepository.save(any(Despesa.class))).thenReturn(despesaAtualizada);

        Despesa resultado = despesaService.atualizarDespesa(1, despesaExistente, 1, 1);

        assertNotNull(resultado);
        assertEquals("Nova despesa", resultado.getDescricao());
        assertEquals(produto, resultado.getProduto());
        assertEquals(caixa, resultado.getCaixa());
        verify(despesaRepository, times(1)).findById(1);
        verify(produtoService, times(1)).produtoPorId(1);
        verify(caixaService, times(1)).caixaPorId(1);
        verify(despesaRepository, times(1)).save(despesaExistente);
    }

    @Test
    void atualizarDespesa_EntidadeNaoEncontrada() {
        when(despesaRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(EntidadeNaoEncontradaException.class, () -> despesaService.atualizarDespesa(1, new Despesa(), null, null));
        verify(despesaRepository, times(1)).findById(1);
    }

    @Test
    void deletarDespesa_Sucesso() {
        when(despesaRepository.existsById(1)).thenReturn(true);

        despesaService.deletarDespesa(1);

        verify(despesaRepository, times(1)).existsById(1);
        verify(despesaRepository, times(1)).deleteById(1);
    }

    @Test
    void deletarDespesa_EntidadeNaoEncontrada() {
        when(despesaRepository.existsById(1)).thenReturn(false);

        assertThrows(EntidadeNaoEncontradaException.class, () -> despesaService.deletarDespesa(1));
        verify(despesaRepository, times(1)).existsById(1);
    }

    @Test
    void totalDespesasMensal_Sucesso() {
        LocalDate inicio = LocalDate.of(2024, 1, 1);
        LocalDate fim = LocalDate.of(2024, 1, 31);
        when(despesaRepository.getTotalDespesaMensal(inicio, fim)).thenReturn(500.0);

        Double total = despesaService.totalDespesasMensal(inicio, fim);

        assertNotNull(total);
        assertEquals(500.0, total);
        verify(despesaRepository, times(1)).getTotalDespesaMensal(inicio, fim);
    }
}
