package Projeto.Academia.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAcademia {

    private Long idAcademia;
    private String academiaAfiliada;
    private String cnpj;
    private Long idEndereco;
}
