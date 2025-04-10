package origami_flow.salgado_trancas_api.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.repository.ServicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    private final ImagemService imagemService;

    public List<Servico> listar() {
        return servicoRepository.findAll();
    }

    public Servico servicoPorId(Integer idServico) {
        return servicoRepository.findById(idServico).orElseThrow(() -> new EntidadeNaoEncontradaException("servico"));
    }

    public Servico criarServico(Servico servico, MultipartFile imagem) {
        if (servicoRepository.existsByNome(servico.getNome())) {
            throw new EntidadeComConflitoException("serviço");
        }
        if (Objects.nonNull(imagem) && !imagem.isEmpty()) {
            servico.setImagem(imagemService.uploadFile(buildImagem(servico, imagem)));
        }
        return servicoRepository.save(servico);
    }

    private FileRequestDTO buildImagem(Servico servico, MultipartFile imagem) {
        return FileRequestDTO.builder()
              .name(Objects.requireNonNullElse(servico.getNome(), "defaultName"))
              .file(imagem)
              .path("/servicos")
              .build();
    }

    public Servico atualizarServico(Servico novoServico, Integer idServico, MultipartFile imagem) {
        Servico servico = servicoPorId(idServico);
        servico.setNome(novoServico.getNome() != null ? novoServico.getNome() : servico.getNome());
        servico.setDescricao(novoServico.getDescricao() != null ? novoServico.getDescricao() : servico.getDescricao());
        servico.setTempoDuracao(novoServico.getTempoDuracao() != null ? novoServico.getTempoDuracao() : servico.getTempoDuracao());
        servico.setValorMinimoServico(novoServico.getValorMinimoServico() != null ? novoServico.getValorMinimoServico() : servico.getValorMinimoServico());
        servico.setValorMaximoServico(novoServico.getValorMaximoServico() != null ? novoServico.getValorMaximoServico() : servico.getValorMaximoServico());
        servico.setValorSinal(novoServico.getValorSinal() != null ? novoServico.getValorSinal() : servico.getValorSinal());
        servico.setImagem(Objects.nonNull(imagem) && !imagem.isEmpty() ? imagemService.uploadFile(buildImagem(servico, imagem)) : servico.getImagem());

        return servicoRepository.save(servico);
    }

    public void removerServico(Integer idServico) {
        if (!servicoRepository.existsById(idServico)) throw new EntidadeNaoEncontradaException("servico");
        servicoRepository.deleteById(idServico);
    }
}
