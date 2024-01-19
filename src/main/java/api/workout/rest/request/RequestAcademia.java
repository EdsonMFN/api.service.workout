package api.workout.rest.request;

import api.workout.domains.model.EnderecoDTO;
import lombok.Data;

@Data
public class RequestAcademia {

    private Long id;
    private String academiaAfiliada;
    private String cnpj;
    private EnderecoDTO endereco;
}
