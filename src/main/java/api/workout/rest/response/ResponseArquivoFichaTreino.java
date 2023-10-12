package api.workout.rest.response;

import api.workout.domains.model.ArquivoFichaTreinoDTO;
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
