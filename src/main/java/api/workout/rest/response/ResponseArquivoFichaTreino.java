package api.workout.rest.response;

import api.workout.rest.DTO.ArquivoFichaTreinoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseArquivoFichaTreino {

    private ArquivoFichaTreinoDTO arquivoFichaTreinoDTO;

    public ResponseArquivoFichaTreino(ArquivoFichaTreinoDTO arquivoFichaTreinoDTO) {
        this.arquivoFichaTreinoDTO = arquivoFichaTreinoDTO;
    }
}
