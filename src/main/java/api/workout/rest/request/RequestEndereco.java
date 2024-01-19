package api.workout.rest.request;

import lombok.Data;

@Data
public class RequestEndereco {

    private Long id;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;
}
