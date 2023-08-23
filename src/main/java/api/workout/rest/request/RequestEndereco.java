package api.workout.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEndereco {

    private Long idEndereco;
    private String estado;
    private String cidade;
    private String bairro;
    private String numero;
    private String cep;
}
