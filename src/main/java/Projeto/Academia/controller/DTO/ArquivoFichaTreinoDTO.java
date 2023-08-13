package Projeto.Academia.controller.DTO;

import Projeto.Academia.entitys.fichaDeTreino.TipoDeArquivo;
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
