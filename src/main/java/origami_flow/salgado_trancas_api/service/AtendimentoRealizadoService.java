package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.dto.request.ProdutoUtilizadoRequestDTO;
import origami_flow.salgado_trancas_api.entity.*;
import origami_flow.salgado_trancas_api.exceptions.CaixaFechadoException;
import origami_flow.salgado_trancas_api.exceptions.CaixaNaoAbertoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.mapper.ProdutoUtilizadoMapper;
import origami_flow.salgado_trancas_api.repository.AtendimentoRealizadoRepository;
import origami_flow.salgado_trancas_api.repository.AvaliacaoClienteRepository;
import origami_flow.salgado_trancas_api.utils.Calculos;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AtendimentoRealizadoService {

    private final AtendimentoRealizadoRepository atendimentoRealizadoRepository;

    private final ProdutoService produtoService;

    private final ProdutoAtendimentoUtilizadoService produtoAtendimentoUtilizadoService;

    private final CaixaService caixaService;

    private final ClienteService clienteService;

    private final AvaliacaoClienteRepository avaliacaoClienteRepository;

    public List<AtendimentoRealizado> listarAtendimentosRealizados(){
        return atendimentoRealizadoRepository.findAll();
    }

    public AtendimentoRealizado cadastrarAtendimentoRealizado(AtendimentoRealizado atendimentoRealizado, Evento evento, List<ProdutoUtilizadoRequestDTO> produtosUtilizadoRequestDTOS){
        List<ProdutoAtendimentoUtilizado> produtosUtilizado = new ArrayList<>();
        if (!produtosUtilizadoRequestDTOS.isEmpty()) {
            List<Integer> produtoIds = produtosUtilizadoRequestDTOS.stream()
                    .map(ProdutoUtilizadoRequestDTO::getId).toList();

            List<Produto> produtos = produtoService.listarTodosPorId(produtoIds);

            produtosUtilizado.addAll(produtosUtilizadoRequestDTOS.stream()
                    .map(dto -> {
                        Produto produto = produtos.stream()
                                 .filter(p -> p.getId().equals(dto.getId()))
                                .findFirst()
                                .orElseThrow(() -> new EntidadeNaoEncontradaException("produto"));
                        return ProdutoUtilizadoMapper.toEntity(dto, produto);
                    }).toList());
        }

        atendimentoRealizado.setEvento(evento);
        atendimentoRealizado.setCaixa(buscarCaixaDoMes(atendimentoRealizado.getEvento().getDataHoraTermino().toLocalDate()));
        atendimentoRealizado.setCliente(clienteService.clientePorId(evento.getCliente().getId()));
        atendimentoRealizado.setAvaliacao(atendimentoRealizado.getAvaliacao() != null?avaliacaoClienteRepository.findAvaliacaoByAtendimentoRealizadoId(atendimentoRealizado.getId()): null);
        atendimentoRealizado.setReceita(Calculos.calcularReceita(evento, produtosUtilizado));
        atualizarReceitaDespesaDoCaixa(atendimentoRealizado, buscarCaixaDoMes(atendimentoRealizado.getEvento().getDataHoraTermino().toLocalDate()));
        AtendimentoRealizado atendimentoSalvo = atendimentoRealizadoRepository.save(atendimentoRealizado);
        produtoAtendimentoUtilizadoService.registrarProdutoUtilizado(produtosUtilizado, atendimentoSalvo);
        return atendimentoSalvo;
    }

    public AtendimentoRealizado atendimentoRealizadoPorId(Integer id){
        return atendimentoRealizadoRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException("atendimento realizado"));
    }

    public void apagarAtendimentoRealizado(Integer id){
        if (!atendimentoRealizadoRepository.existsById(id)) throw  new EntidadeNaoEncontradaException("atendimento realizado");
        atendimentoRealizadoRepository.deleteById(id);
    }

    public Integer buscarNumeroDeAtendimentoRealizado(int mes, int ano){
        return atendimentoRealizadoRepository.buscarTotalAtendimentoRealizadosNoMes(mes, ano);
    }

    public Integer buscarNumeroDeAtendimentoRealizadoComClientesNovos(int mes, int ano){
        return atendimentoRealizadoRepository.buscarTotalAtendimentoRealizadosNoMesComClientesNovos(mes, ano);
    }

    public String trancaMaisRealizadaoNoMes(int mes, int ano){
        return atendimentoRealizadoRepository.buscarTrancaMaisRealizadaNoMes(mes,ano);
    }

    private Caixa buscarCaixaDoMes(LocalDate data) {
        Caixa caixa = caixaService.buscarCaixaPorMes(data.getMonth().getValue(), data.getYear());
        if (caixa == null) throw new CaixaNaoAbertoException("O caixa do mes ainda não foi aberto!");
        return caixa;
    }

    private void atualizarReceitaDespesaDoCaixa(AtendimentoRealizado atendimento, Caixa caixa) {
        if (caixa.getDataFechamento().isBefore(LocalDate.now(ZoneOffset.of("-03:00")))) throw new  CaixaFechadoException("O caixa já está fechado!");
        caixa.setReceitaTotal(caixa.getReceitaTotal() + atendimento.getReceita());
        if (atendimento.getEvento().getAuxiliar() != null) {
            caixa.setDespesaTotal(caixa.getDespesaTotal() + atendimento.getEvento().getAuxiliar().getValorMaoDeObra());
        }
        caixaService.atualizarCaixa(caixa.getId(), caixa, null);
    }
}
