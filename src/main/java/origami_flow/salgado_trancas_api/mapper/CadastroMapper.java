package origami_flow.salgado_trancas_api.mapper;

import origami_flow.salgado_trancas_api.dto.request.CadastroRequestDTO;
import origami_flow.salgado_trancas_api.dto.response.cadastro.CadastroCriptografadoResponse;
import origami_flow.salgado_trancas_api.entity.Cliente;

public class CadastroMapper {
    public static Cliente toClienteEntity(CadastroRequestDTO cadastroRequestDTO , CadastroCriptografadoResponse criptografadoResponse) {
        return Cliente.builder()
                .nome(criptografadoResponse.getNome())
                .email(criptografadoResponse.getEmail())
                .senha(criptografadoResponse.getSenha())
                .dataNascimento(cadastroRequestDTO.getDataNascimento())
                .telefone(cadastroRequestDTO.getTelefone())
                .genero(cadastroRequestDTO.getGenero())
                .ocupacao(cadastroRequestDTO.getOcupacao())
                .tipoCabelo(cadastroRequestDTO.getTipoCabelo())
                .corCabelo(cadastroRequestDTO.getCorCabelo())
                .build();
    }

    public static Cliente toClienteEntity(CadastroRequestDTO cadastroRequestDTO) {
        return Cliente.builder()
                .nome(cadastroRequestDTO.getNome())
                .email(cadastroRequestDTO.getEmail())
                .senha(cadastroRequestDTO.getSenha())
                .dataNascimento(cadastroRequestDTO.getDataNascimento())
                .telefone(cadastroRequestDTO.getTelefone())
                .genero(cadastroRequestDTO.getGenero())
                .ocupacao(cadastroRequestDTO.getOcupacao())
                .tipoCabelo(cadastroRequestDTO.getTipoCabelo())
                .corCabelo(cadastroRequestDTO.getCorCabelo())
                .build();
    }
}
