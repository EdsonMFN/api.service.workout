package api.workout.rest.response;

import api.workout.domains.model.AcademiaDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAcademia {

    private AcademiaDTO academiaDTO;

    public ResponseAcademia(AcademiaDTO academiaDTO) {
        this.academiaDTO = academiaDTO;
    }
}
