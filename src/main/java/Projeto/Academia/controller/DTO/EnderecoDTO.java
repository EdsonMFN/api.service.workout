package Projeto.Academia.controller.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EnderecoDTO {

    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;
}
