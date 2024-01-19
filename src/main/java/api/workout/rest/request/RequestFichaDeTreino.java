package api.workout.rest.request;

import api.workout.domains.model.AcademiaDTO;
import api.workout.domains.model.AlunoDTO;
import api.workout.domains.model.ProfessorDTO;
import api.workout.enums.Exercicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFichaDeTreino {

    private Long id;
    private Exercicio exercicio;
    private ProfessorDTO professor;
    private AlunoDTO aluno;
    private AcademiaDTO academia;
}
