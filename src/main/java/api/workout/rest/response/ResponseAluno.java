package api.workout.rest.response;

import api.workout.domains.model.AlunoDTO;
import lombok.Data;

@Data
public class ResponseAluno {

    private AlunoDTO alunoDTO;

    public ResponseAluno(AlunoDTO alunoDTO) {
        this.alunoDTO=alunoDTO;
    }

}
