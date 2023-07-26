package Projeto.Academia.service.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEndereco {

    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;
}
