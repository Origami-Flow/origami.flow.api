package origami_flow.salgado_trancas_api.mapper;

import org.mapstruct.Mapper;
import origami_flow.salgado_trancas_api.dto.request.cadastro.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.request.cliente.ClienteRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cliente.ClienteDetalheResponseDTO;
import origami_flow.salgado_trancas_api.entity.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    Cliente toClienteEntity(ClienteRequestDTO clienteRequestDTO);

    Cliente toClienteEntity(CadastroRequestDTO clienteRequestDTO);

    ClienteDetalheResponseDTO toClienteDetalheResponseDTO(Cliente cliente);
}
