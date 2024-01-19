package api.workout.rest.request;

import api.workout.domains.model.AcademiaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProfessor {

    private Long id;
    private String nome;
    private String cpf;
    private String cref;
    private AcademiaDTO academia;

}
