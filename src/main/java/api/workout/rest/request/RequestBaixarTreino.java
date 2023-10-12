package api.workout.rest.request;

import api.workout.enums.TipoDeArquivo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBaixarTreino {

    private Long id;
    private Long idFichaDeTreino;
    private TipoDeArquivo tipoDeArquivo;
}
