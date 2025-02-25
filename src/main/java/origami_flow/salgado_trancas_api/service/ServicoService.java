package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ServicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Servico servicoPorId(Integer idServico) {
        return servicoRepository.findById(idServico).orElseThrow(()-> new EntidadeNaoEncontradaException("servico"));
    }

    public  Servico criarServico(Servico servico) {
        if (servicoRepository.existsByNome(servico.getNome())) throw new EntidadeComConflitoException("serviço");
        return servicoRepository.save(servico);
    }

    public Servico atualizarServico(Servico novoServico, Integer idServico) {
        Servico servico = servicoPorId(idServico);
        servico.setNome(novoServico.getNome() != null ? novoServico.getNome() : servico.getNome());
        servico.setDescricao(novoServico.getDescricao() != null ? novoServico.getDescricao() : servico.getDescricao());
        servico.setTempoDuracao(novoServico.getTempoDuracao() != null ? novoServico.getTempoDuracao() : servico.getTempoDuracao());
        servico.setValorMinimoServico(novoServico.getValorMinimoServico() != null ? novoServico.getValorMinimoServico() : servico.getValorMinimoServico());
        servico.setValorMaximoervico(novoServico.getValorMaximoervico() != null ? novoServico.getValorMaximoervico() : servico.getValorMaximoervico());
        servico.setValorSinal(novoServico.getValorSinal() != null ? novoServico.getValorSinal() : servico.getValorSinal());
        return servicoRepository.save(servico);
    }

    public void removerServico(Integer idServico) {
        if (!servicoRepository.existsById(idServico)) throw new EntidadeNaoEncontradaException("servico");
        servicoRepository.deleteById(idServico);
    }
}
