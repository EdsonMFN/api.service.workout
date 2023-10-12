package api.workout.rest.response;

import api.workout.domains.model.ProfessorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseProfessor {

    private ProfessorDTO professorDTO;

    public ResponseProfessor(ProfessorDTO professorDTO) {
        this.professorDTO = professorDTO;
    }
}
