package Projeto.Academia.controller.request;

import Projeto.Academia.entitys.fichaDeTreino.TipoDeArquivo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestBaixarTreino {

    private Long id;
    private Long idFichaDeTreino;
    private TipoDeArquivo tipoDeArquivo;
}
