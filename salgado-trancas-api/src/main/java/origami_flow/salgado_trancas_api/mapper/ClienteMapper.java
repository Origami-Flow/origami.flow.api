package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.ClienteAtualizacaoRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toClienteEntity(CadastroRequestDTO clienteRequestDTO);

    Cliente toClienteEntity(ClienteAtualizacaoRequestDTO clienteAtualizacaoRequestDTO);

    ClienteDetalheResponseDTO toClienteDetalheResponseDTO(Cliente cliente);
}
