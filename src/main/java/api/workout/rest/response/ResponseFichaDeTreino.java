package api.workout.rest.response;

import api.workout.domains.model.FichaDeTreinoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseFichaDeTreino {

    private FichaDeTreinoDTO fichaDeTreinoDTO;

    public ResponseFichaDeTreino(FichaDeTreinoDTO fichaDeTreinoDTO) {
        this.fichaDeTreinoDTO = fichaDeTreinoDTO;
    }


}
