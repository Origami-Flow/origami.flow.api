package origami_flow.salgado_trancas_api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import origami_flow.salgado_trancas_api.constans.TipoEventoEnum;
import origami_flow.salgado_trancas_api.dto.response.FinancaResponseDTO;
import origami_flow.salgado_trancas_api.entity.AtendimentoRealizado;
import origami_flow.salgado_trancas_api.entity.Despesa;
import origami_flow.salgado_trancas_api.repository.AtendimentoRealizadoRepository;
import origami_flow.salgado_trancas_api.repository.DespesaRepository;
import origami_flow.salgado_trancas_api.utils.FilaObj;
import origami_flow.salgado_trancas_api.utils.PilhaObj;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinancaService {

    private final DespesaRepository despesaRepository;

    private final AtendimentoRealizadoRepository atendimentoRealizadoRepository;
    private PilhaObj<FinancaResponseDTO.DespesaDetalhadaDTO> pilhaDespesas;
    private FilaObj<FinancaResponseDTO.AtendimentoDetalhadoDTO> filaAtendimentos;

    @Transactional(readOnly = true)
    public FinancaResponseDTO gerarRelatorioMensal(int mes, int ano) {
        pilhaDespesas = new PilhaObj<>(atendimentoRealizadoRepository.findAll().size() + despesaRepository.findAll().size());
        filaAtendimentos = new FilaObj<>(100);

        List<Despesa> despesas = despesaRepository.findByMesEAno(mes, ano);
        List<FinancaResponseDTO.DespesaDetalhadaDTO> despesasDetalhadas = despesas.stream()
                .map(d -> {
                    FinancaResponseDTO.DespesaDetalhadaDTO dto = new FinancaResponseDTO.DespesaDetalhadaDTO();
                    dto.setNome(d.getProduto() != null ? d.getProduto().getNome() : d.getNome());
                    dto.setDescricao(d.getDescricao());
                    dto.setValor(d.getValor());
                    dto.setData(d.getData());
                    pilhaDespesas.push(dto);
                    return dto;
                }).collect(Collectors.toList());

        List<AtendimentoRealizado> atendimentos = atendimentoRealizadoRepository.atendimentosPorMes(mes, ano);
        List<FinancaResponseDTO.AtendimentoDetalhadoDTO> atendimentosDetalhados = atendimentos.stream()
                .map(a -> {
                    FinancaResponseDTO.AtendimentoDetalhadoDTO dto = new FinancaResponseDTO.AtendimentoDetalhadoDTO();
                    dto.setNomeCliente(a.getEvento().getCliente().getNome());
                    dto.setTipoAtendimento(String.valueOf(a.getEvento().getTipoEvento().equals(TipoEventoEnum.ATENDIMENTO)));
                    dto.setData(a.getEvento().getDataHoraTermino().toLocalDate());
                    dto.setValor(a.getReceita());
                    filaAtendimentos.insert(dto);
                    return dto;
                }).collect(Collectors.toList());

        Double totalDespesas = despesas.stream().mapToDouble(Despesa::getValor).sum();
        Double totalReceitas = atendimentos.stream().mapToDouble(AtendimentoRealizado::getReceita).sum();
        Double lucro = totalReceitas - totalDespesas;

        FinancaResponseDTO resposta = new FinancaResponseDTO();
        resposta.setLucro(lucro);
        resposta.setDespesas(despesasDetalhadas);
        resposta.setAtendimentos(atendimentosDetalhados);

        return resposta;
    }

    public String exibirPilhaDespesas() {
        StringBuilder sb = new StringBuilder("Pilha de Despesas:\n");
        while (!pilhaDespesas.isEmpty()) {
            sb.append(pilhaDespesas.pop()).append("\n");
        }
        return sb.toString();
    }

    public String exibirFilaAtendimentos() {
        StringBuilder sb = new StringBuilder("Fila de Atendimentos:\n");
        while (!filaAtendimentos.isEmpty()) {
            sb.append(filaAtendimentos.poll()).append("\n");
        }
        return sb.toString();
    }
}
