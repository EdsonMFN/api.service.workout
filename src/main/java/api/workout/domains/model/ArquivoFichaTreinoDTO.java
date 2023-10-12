package api.workout.domains.model;

import api.workout.enums.TipoDeArquivo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ArquivoFichaTreinoDTO {

    private Long id;
    private FichaDeTreinoDTO fichaDeTreinoDTO;
    private TipoDeArquivo tipoDeArquivo;
    private String base64;

}
