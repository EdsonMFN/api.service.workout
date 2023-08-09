package Projeto.Academia.controller.response;

import Projeto.Academia.controller.DTO.FichaDeTreinoDTO;
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
