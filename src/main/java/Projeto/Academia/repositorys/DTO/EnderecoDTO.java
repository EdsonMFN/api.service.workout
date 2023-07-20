package Projeto.Academia.repositorys.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {

    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;
}
