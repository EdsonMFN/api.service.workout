package api.workout.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProfessor {

    private Long idProfessor;
    private String nome;
    private String cpf;
    private String cref;
    private Long idAcademia;

}
