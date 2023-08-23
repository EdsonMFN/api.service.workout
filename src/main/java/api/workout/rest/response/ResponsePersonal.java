package api.workout.rest.response;

import api.workout.rest.DTO.PersonalDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsePersonal {

    private PersonalDTO personalDTO;

    public ResponsePersonal(PersonalDTO personalDTO) {
        this.personalDTO = personalDTO;
    }
}
