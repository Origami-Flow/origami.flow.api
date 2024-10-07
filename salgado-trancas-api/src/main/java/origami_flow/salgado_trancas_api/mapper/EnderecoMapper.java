package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.request.endereco.EnderecoRequestDTO;
import origami_flow.salgado_trancas_api.entity.Endereco;

public class EnderecoMapper {

    public Endereco toEnderecoEntity(EnderecoRequestDTO dto) {
        if (dto == null) return null;
        return Endereco.builder()
                .tipo(dto.getTipo())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .logradouro(dto.getLogradouro())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .bairro(dto.getBairro())
                .estado(dto.getEstado())
                .build();
    }

}
