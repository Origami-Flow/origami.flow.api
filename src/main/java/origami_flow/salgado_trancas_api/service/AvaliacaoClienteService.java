package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Avaliacao;
import origami_flow.salgado_trancas_api.entity.Cliente;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.exceptions.RequisicaoErradaException;
import origami_flow.salgado_trancas_api.repository.AvaliacaoClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoClienteService {

    private final AvaliacaoClienteRepository avaliacaoClienteRepository;

    private final ClienteService clienteService;

    private final SalaoService salaoService;

    private final AtendimentoRealizadoService atendimentoRealizadoService;

    public List<Avaliacao> listarAaliacao() {
        return avaliacaoClienteRepository.findAll();
    }

    public Avaliacao avaliacaoPorId(Integer id) {
        return avaliacaoClienteRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("avaliacao"));
    }

    public Avaliacao criarAvaliacao(Avaliacao avaliacao, Integer idAtendimentoRealizado, Integer idCliente, Integer idSalao) {
        if (idSalao == null || idCliente == null || idAtendimentoRealizado == null || avaliacao.getNota() == null || avaliacao.getNota() < 0)
            throw new RequisicaoErradaException();
        avaliacao.setSalao(salaoService.salaoPorId(idSalao));
        avaliacao.setCliente(clienteService.clientePorId(idCliente));
        avaliacao.setAtendimentoRealizado(atendimentoRealizadoService.atendimentoRealizadoPorId(idAtendimentoRealizado));

        return avaliacaoClienteRepository.save(avaliacao);
    }

    public Avaliacao atualizarAvaliacao(Integer id, Avaliacao avaliacao, Integer idAtendimentoRealizado, Integer idCliente, Integer idSalao) {
        if (!avaliacaoClienteRepository.existsById(id)) throw new EntidadeNaoEncontradaException("avaliacao");
        Avaliacao avaliacaoAtualizar = avaliacaoClienteRepository.findById(id).orElse(null);
        avaliacaoAtualizar.setAtendimentoRealizado(idAtendimentoRealizado != null? atendimentoRealizadoService.atendimentoRealizadoPorId(idAtendimentoRealizado): avaliacaoAtualizar.getAtendimentoRealizado());
        avaliacaoAtualizar.setCliente(idCliente != null? clienteService.clientePorId(idCliente) : avaliacaoAtualizar.getCliente());
        avaliacaoAtualizar.setSalao(idSalao != null? salaoService.salaoPorId(idSalao): avaliacaoAtualizar.getSalao());
        avaliacaoAtualizar.setComentario(avaliacao.getComentario() != null? avaliacao.getComentario(): avaliacaoAtualizar.getComentario());
        avaliacaoAtualizar.setNota(avaliacao.getNota() != null? avaliacao.getNota(): avaliacaoAtualizar.getNota());

        avaliacao.setId(id);

        return avaliacaoClienteRepository.save(avaliacao);
    }

    public void deletarAvaliacao(Integer id) {
        if (!avaliacaoClienteRepository.existsById(id)) throw new EntidadeNaoEncontradaException("avaliacao");

        avaliacaoClienteRepository.deleteById(id);
    }
}
