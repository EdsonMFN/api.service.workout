package api.workout.rest.DTO;

import api.workout.entitys.fichaDeTreino.Exercicio;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FichaDeTreinoDTO {

    private Long id;
    private Exercicio exercicio;
    private ProfessorDTO professor;
    private AlunoDTO aluno;
    private AcademiaDTO academiaAfiliada;
}
