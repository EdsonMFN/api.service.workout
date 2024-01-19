package api.workout.rest.request;

import api.workout.enums.Exercicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFichaDeTreino {

    private Long id;
    private Exercicio exercicio;
    private Long idProfessor;
    private String cpfAluno;
    private Long idAcademia;
}
