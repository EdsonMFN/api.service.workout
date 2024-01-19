package api.workout.domains.model;

import api.workout.domains.entitys.FichaDeTreino;
import api.workout.enums.Exercicio;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FichaDeTreinoDTO {

    private Long id;
    private Exercicio exercicio;
    private ProfessorDTO professor;
    private AlunoDTO aluno;
    private AcademiaDTO academiaAfiliada;

    public FichaDeTreinoDTO(FichaDeTreino fichaDeTreino) {
        builder(fichaDeTreino);
    }

    public FichaDeTreinoDTO builder(FichaDeTreino fichaDeTreino) {
        return FichaDeTreinoDTO.builder()
                .id(fichaDeTreino.getId())
                .exercicio(fichaDeTreino.getExercicio())
                .professor(new ProfessorDTO())
                .aluno(new AlunoDTO())
                .academiaAfiliada(new AcademiaDTO())
                .build();
    }
}
