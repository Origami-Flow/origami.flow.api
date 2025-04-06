package origami_flow.salgado_trancas_api.service;

import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import origami_flow.salgado_trancas_api.dto.request.FileRequestDTO;
import origami_flow.salgado_trancas_api.entity.Estoque;
import origami_flow.salgado_trancas_api.entity.Imagem;
import origami_flow.salgado_trancas_api.entity.Produto;
import origami_flow.salgado_trancas_api.entity.Salao;
import origami_flow.salgado_trancas_api.entity.Servico;
import origami_flow.salgado_trancas_api.exceptions.EntidadeComConflitoException;
import origami_flow.salgado_trancas_api.exceptions.EntidadeNaoEncontradaException;
import origami_flow.salgado_trancas_api.exceptions.RequisicaoErradaException;
import origami_flow.salgado_trancas_api.repository.ProdutoRepository;
import origami_flow.salgado_trancas_api.utils.Lista;
import origami_flow.salgado_trancas_api.utils.PesquisaBinaria;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final EstoqueService estoqueService;

    private final SalaoService salaoService;

    private final ImagemService imagemService;

    public List<Produto> listarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Produto produtoPorId(Integer id){
        return produtoRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException("produto"));
    }

    private FileRequestDTO buildImagem(Produto produto, MultipartFile imagem) {
        return FileRequestDTO.builder()
              .name(Objects.requireNonNullElse(produto.getNome(), "defaultName"))
              .file(imagem)
              .path("/produto")
              .build();
    }

    public Produto cadastrarProduto(Produto produto, Integer idSalao ,Integer quantidade, MultipartFile imagem){
        Salao salao = salaoService.salaoPorId(idSalao);
        if(produtoRepository.existsByNome(produto.getNome())) throw new EntidadeComConflitoException("produto");
        if(quantidade == null || quantidade < 0) throw new RequisicaoErradaException("Quantidade de produtos inválida");
        if (Objects.nonNull(imagem) && !imagem.isEmpty()) {
             produto.setImagem(imagemService.uploadFile(buildImagem(produto, imagem)));
        }
        Produto produtoSalvo = produtoRepository.save(produto);
        Estoque estoque = Estoque.builder().quantidade(quantidade).produto(produtoSalvo).salao(salao).build();
        estoqueService.cadastrarProdutoNoEstoque(estoque);
        return produtoSalvo;
    }

    public Produto atualizarProduto(Integer id, Produto produto, MultipartFile file){
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("produto");
        produto.setId(id);
        var image = produto.getImagem();
        if (Objects.nonNull(file) && !file.isEmpty()) {
            image = imagemService.uploadFile(buildImagem(produto, file));
        }
        produto.setImagem(image);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id){
        if (!produtoRepository.existsById(id)) throw new EntidadeNaoEncontradaException("produto");
        produtoRepository.deleteById(id);
    }

    public List<Produto> buscarProdutoNome(String nome) {
        return produtoRepository.findAllByOrderByNome(nome);
    }

    public List<Produto> listarTodosPorId(List<Integer> ids) {
        return produtoRepository.findAllById(ids);
    }
}
