package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.request.cliente.ClienteRequestDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;

public class ClienteMapper {
    public Cliente toClienteEntity(ClienteRequestDTO clienteRequest) {
        if (clienteRequest == null) return null;
        return Cliente.builder()
                .nome(clienteRequest.getNome())
                .email(clienteRequest.getEmail())
                .senha(clienteRequest.getSenha())
                .dtNascimento(clienteRequest.getDtNascimento())
                .telefone(clienteRequest.getTelefone())
                .corCabelo(clienteRequest.getCorCabelo())
                .comprimentoCabelo(clienteRequest.getComprimentoCabelo())
                .progressiva(clienteRequest.isProgressiva())
                .primeiraTranca(clienteRequest.isPrimeiraTranca())
                .ocupacao(clienteRequest.getOcupacao())
                .build();
    }
}
