package api.workout.domains.model;

import api.workout.enums.TipoDeArquivo;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArquivoFichaTreinoDTO {

    private Long id;
    private FichaDeTreinoDTO fichaDeTreinoDTO;
    private TipoDeArquivo tipoDeArquivo;
    private String base64;



}
