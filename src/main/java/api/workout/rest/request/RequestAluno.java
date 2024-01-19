package api.workout.rest.request;

import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.ProfessorDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestAluno {

    private String nome;
    private String cpf;
    private AcademiaDTO academia;
    private ProfessorDTO professor;
    private ProfessorDTO personal;

}
