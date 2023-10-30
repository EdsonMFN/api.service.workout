package api.workout.domains.entitys;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InscricaoAluno {

    private String dataDaInscricao;
    private String academia;
    private String aluno;
    private String professor;

}
