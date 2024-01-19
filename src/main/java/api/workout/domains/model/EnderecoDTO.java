package api.workout.domains.model;

import api.workout.domains.entitys.Endereco;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnderecoDTO {

    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;

    public EnderecoDTO(Endereco endereco) {
        builder(endereco);
    }

    public EnderecoDTO builder(Endereco endereco) {
       return EnderecoDTO.builder()
                .id(endereco.getId())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .bairro(endereco.getBairro())
                .numero(endereco.getNumero())
                .cep(endereco.getCep())
                .build();
    }
}
